package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.ConstantVariables;
import com.sunil.myportal.constant.ExceptionConstant;
import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.BaseResponse;
import com.sunil.myportal.dto.TaskDefinition;
import com.sunil.myportal.model.Emi;
import com.sunil.myportal.model.Loan;
import com.sunil.myportal.model.TaskMaster;
import com.sunil.myportal.repository.EmiRepository;
import com.sunil.myportal.repository.LoanRepository;
import com.sunil.myportal.repository.TaskRepository;
import com.sunil.myportal.service.EmailService;
import com.sunil.myportal.service.TaskDefinitionBean;
import com.sunil.myportal.service.TaskSchedulingService;
import com.sunil.myportal.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

import static com.google.common.primitives.Longs.max;

@Service
public class TaskSchedulingServiceImpl implements TaskSchedulingService {

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    EmiRepository emiRepository;

    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public TaskSchedulingServiceImpl(TaskScheduler taskScheduler, EmailService emailService,
                                     TaskRepository taskRepository, LoanRepository loanRepository,
                                     EmiRepository emiRepository) {
        this.taskScheduler = taskScheduler;
        this.emailService = emailService;
        this.taskRepository = taskRepository;
        this.loanRepository = loanRepository;
        this.emiRepository = emiRepository;
    }


    public void scheduleATask(TaskDefinitionBean tasklet, String cronExpression) {
        String jobId = CommonUtil.generateUuid();
        System.out.println("Scheduling task with job id: " + jobId + " and cron expression: " + cronExpression);

        List<TaskMaster> pendingTaskList = this.taskRepository.findAllByTaskStatusAndIsDeleted(StatusConstant.STATUS_PENDING, false);
        for (TaskMaster pendingTask : pendingTaskList) {
            TaskDefinition taskDefinition = new TaskDefinition();
            taskDefinition.setData(pendingTask.getDescription());
            taskDefinition.setActionType(pendingTask.getDescription());
            taskDefinition.setData(pendingTask.getDescription());

            System.out.println("pendingTask: " + pendingTask.getDescription());
            tasklet.setTaskDefinition(taskDefinition);
            ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
            jobsMap.put(jobId, scheduledTask);
        }

    }

    public void removeScheduledTask(String jobId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }

    @Override
    public BaseResponse sendTaskNotification() {
        LocalDate taskFirstDate = CommonUtil.getFirstDateOfTheMonth(LocalDate.now());
        LocalDate taskLastDate = CommonUtil.getLastDateOfTheMonth(LocalDate.now());
        List<TaskMaster> pendingTaskList = new ArrayList<>(taskRepository.
                findAllByIsDeletedAndByPlannedEndDate(false, taskFirstDate, taskLastDate));

        BaseResponse response = new BaseResponse();
        String content;
        String subject;
        String receiverEmail = "sunilkmr5775@gmail.com";
//        List<TaskMaster> pendingTaskList = taskRepository.findAllByTaskStatusAndIsDeleted(StatusConstant.STATUS_PENDING, false);
        if(!pendingTaskList.isEmpty() || pendingTaskList.size() > 0){
            for (TaskMaster pendingTask : pendingTaskList) {
                subject = pendingTask.getTitle();
                Long diff = CommonUtil.findDateDifferenceWithCurrentDate(pendingTask.getPlannedEndDate());
                if (diff >= 0 && pendingTask.isReminderRequired()) {
                    content = ConstantVariables.TASK_NOTIFICATION_CONTENT;
                    content = content.replace("[[name]]", pendingTask.getCreatedBy());
                    content = content.replace("[[task]]", pendingTask.getTitle());
                    content = content.replace("[[plannedEndDate]]", pendingTask.getPlannedEndDate().toString());
                    boolean currentDayReminderSent = false;
                    currentDayReminderSent = pendingTask.getIsCurrentDayReminderSent() == null ? false : pendingTask.getIsCurrentDayReminderSent();
                    if(!currentDayReminderSent) {
                        response = emailService.sendEmailNotification(content, subject, receiverEmail);
                    }
                    updateTaskReminderCounter(pendingTask);
                }
            }
        }
        return response;
    }

    @Override
    public BaseResponse updateEmiAndSendEmiPaidNotification() {
        BaseResponse response = new BaseResponse();
        String content;
        String subject;
        String receiverEmail = "sunilkmr5775@gmail.com";
        Loan loan = new Loan();
        try {
            List<Loan> activeLoans = loanRepository.findAllByStatus(StatusConstant.STATUS_ACTIVE);
            for (Loan activeLoan : activeLoans) {
                loan = loanRepository.findByLoanNo(activeLoan.getLoanNo());
                LocalDate today = LocalDate.now();
                LocalDate fistDayOfCurrentMonth = CommonUtil.getFirstDateOfTheMonth(today);
                LocalDate lastDayOfCurrentMonth = CommonUtil.getLastDateOfTheMonth(today);
                Emi pendingEmi = emiRepository.findByLoanNoAndStatusAndEmiDateBetween(activeLoan.getLoanNo(), StatusConstant.STATUS_UNPAID, fistDayOfCurrentMonth, lastDayOfCurrentMonth);
                if (pendingEmi != null) {
                    boolean isLoanDetailsUpdated = false;
                    BigDecimal defaultAmount = new BigDecimal("0.00");
                    pendingEmi.setLoan(activeLoan);
                    pendingEmi.setLoanNo(activeLoan.getLoanNo());
                    pendingEmi.setStatus(StatusConstant.STATUS_PAID);
                    pendingEmi.setEmiStatus(true);
                    isLoanDetailsUpdated = updateLoanCounter(loan);
                    if (isLoanDetailsUpdated) {
                        this.emiRepository.save(pendingEmi);
                    }
                    if (activeLoan.getTotalEmi() == pendingEmi.getNoOfPayment()) {
                        activeLoan.setStatus(StatusConstant.STATUS_CLOSED);
                        this.loanRepository.save(activeLoan);
                    }
//				Insert emi details for next month in advance keeping status as UNPAID so that scheduler can
//				easily pick it up and update its status as PAID in next month
                    Emi nextMonthEmi = new Emi();
                    nextMonthEmi.setLoan(activeLoan);
                    nextMonthEmi.setLoanNo(activeLoan.getLoanNo());
                    nextMonthEmi.setEmiAmount(pendingEmi.getEmiAmount());
                    nextMonthEmi.setLateFineCharge(defaultAmount);
                    nextMonthEmi.setInterestAmount(defaultAmount.add(pendingEmi.getEmiAmount()));
                    nextMonthEmi.setEmiDate(pendingEmi.getEmiDate().plusMonths(1));
                    nextMonthEmi.setEmiStatus(false);
                    nextMonthEmi.setStatus(StatusConstant.STATUS_UNPAID);
                    nextMonthEmi.setTotalAmount(defaultAmount.add(pendingEmi.getEmiAmount()));
                    nextMonthEmi.setCreatedBy("sunilkumar5775");
                    nextMonthEmi.setCreatedDate(LocalDateTime.now());
                    nextMonthEmi.setNoOfPayment(activeLoan.getEmiPaid() + 1);
                    emiRepository.save(nextMonthEmi);

                    subject = "EMI paid for Loan No: " + activeLoan.getLoanNo() + " for month of: " +
                            LocalDate.now().getMonth() + "-" + LocalDate.now().getYear();
                    content = ConstantVariables.PENDING_EMI_NOTIFICATION_CONTENT;
                    content = content.replace("[[name]]", activeLoan.getCreatedBy());
                    content = content.replace("[[loan_no]]", String.valueOf(activeLoan.getLoanNo()));
                    content = content.replace("[[current_date]]", String.valueOf(LocalDate.now().getMonth() + "-" + LocalDate.now().getYear()));
                    response = emailService.sendEmailNotification(content, subject, receiverEmail);
                } else {
                    response.setStatus(ExceptionConstant.SUCCESS_ED);
                    response.setErrorCode(ExceptionConstant.SUCCESS_EC);
                    response.setErrorCode(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_ED);
                }
            }
        } catch (Exception e) {
            System.out.println("Inside addEmi() in EmiServiceImpl at line no 50: " + e.getMessage());
        }

        return response;
    }

    @Override
    public void resetTaskNotification() {
        try {
            List<TaskMaster> taskList = taskRepository.findAllByIsCurrentDayReminderSent(true);
            for (TaskMaster task : taskList) {
                if (task.getIsCurrentDayReminderSent() == true)
                    task.setIsCurrentDayReminderSent(false);
            }
        } catch (Exception e) {
            System.out.println("Inside resetTaskNotification() in TaskSchedulingServiceImpl: " + e.getMessage());
        }
    }

    public boolean updateTaskReminderCounter(TaskMaster taskMaster) {
        Long taskId = 0L;
        boolean flag = false;
        if (taskMaster.getTotalReminder() == taskMaster.getReminderSent()) {
            taskMaster.setReminderRequired(false);
        } else {
//          taskMaster.setTotalReminder(taskMaster.getTotalReminder() - 1);
            taskMaster.setReminderSent(taskMaster.getReminderSent() == 0 ? 1 : taskMaster.getReminderSent() + 1);
            taskMaster.setReminderPending(taskMaster.getReminderPending() - 1);
            taskMaster.setModifiedBy("sunilkmr5775");
            taskMaster.setModifiedDate(LocalDateTime.now());
            taskMaster.setIsCurrentDayReminderSent(true);
        }
        taskId = taskRepository.save(taskMaster).getId();
        flag = taskId > 0 ? true : false;

        return flag;
    }

    public boolean updateLoanCounter(Loan loanDetails) {
        Long loanId = 0L;
        boolean flag = false;
        loanDetails.setEmiPaid(loanDetails.getEmiPaid() + 1);
        loanDetails.setEmiRemaining(loanDetails.getTotalEmi() - loanDetails.getEmiPaid());
//			loanDetails.setEmiAmount(emi.getEmiAmount());
//			loanDetails.setInterestPaid(loanDetails.getInterestPaid() == null ? new BigDecimal("0.00") : loanDetails.getInterestPaid().add(emi));
        loanDetails.setModifiedBy("sunilkmr5775");
        loanDetails.setModifiedDate(LocalDateTime.now());
        loanId = loanRepository.save(loanDetails).getLoanId();

        flag = loanId > 0 ? true : false;

        return flag;
    }
}
