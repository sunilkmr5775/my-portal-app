package com.sunil.myportal.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.sunil.myportal.service.EmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.model.Emi;
import com.sunil.myportal.model.Loan;
import com.sunil.myportal.repository.EmiRepository;
import com.sunil.myportal.repository.LoanRepository;

@Service
public class EmiServiceImpl implements EmiService {

    @Autowired
    private EmiRepository emiRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Emi addEmi(Emi emi) {
        Loan loan = new Loan();
        Emi emi1 = new Emi();
        boolean isLoanDetailsUpdated = false;
        try {
            loan = loanRepository.findByLoanNo(emi.getLoanNo());
            BigDecimal defaultAamount = new BigDecimal("0.00");
            isLoanDetailsUpdated = updateLoanCounter(loan);
            //	if(isLoanDetailsUpdated) {
            emi.setLoan(loan);
            emi.setEmiAmount(emi.getEmiAmount());
            emi.setEmiDate(emi.getEmiDate());
            emi.setLateFineCharge(defaultAamount);
            emi.setInterestAmount(defaultAamount.add(emi.getEmiAmount()));
            emi.setStatus(
                    emi.isEmiStatus() == true ? StatusConstant.STATUS_PAID
                            : emi.isEmiStatus() == false ? StatusConstant.STATUS_UNPAID.toString()
                            : StatusConstant.STATUS_UNKNOWN);
            emi.setTotalAmount(defaultAamount.add(emi.getEmiAmount()));
            emi.setCreatedBy("sunilkumar5775");
            emi.setCreatedDate(LocalDateTime.now());
            emi.setNoOfPayment(loan.getEmiPaid());

            emi1 = this.emiRepository.save(emi);
            if (loan.getTotalEmi() == emi.getNoOfPayment()) {
                loan.setStatus(StatusConstant.STATUS_CLOSED);
                this.loanRepository.save(loan);
            }
//				Insert emi details for next month in advance keeping status as UNPAID so that scheduler can
//				easily pick it up and update its status as PAID in next month
            Emi nextMonthEmi = new Emi();
//            Loan loanNextEmi = new Loan();
//            loanNextEmi = loanRepository.findByLoanNo(emi.getLoanNo());
//            updateLoanCounter(loanNextEmi);
            nextMonthEmi.setLoan(loan);
            nextMonthEmi.setEmiAmount(emi.getEmiAmount());
            nextMonthEmi.setLateFineCharge(defaultAamount);
            nextMonthEmi.setInterestAmount(defaultAamount.add(emi.getEmiAmount()));
            nextMonthEmi.setEmiDate(emi.getEmiDate().plusMonths(1));
            nextMonthEmi.setEmiStatus(false);
            nextMonthEmi.setStatus(StatusConstant.STATUS_UNPAID);
            nextMonthEmi.setTotalAmount(defaultAamount.add(emi.getEmiAmount()));
            nextMonthEmi.setCreatedBy("sunilkumar5775");
            nextMonthEmi.setCreatedDate(LocalDateTime.now());
            nextMonthEmi.setNoOfPayment(loan.getEmiPaid()+1);
            emiRepository.save(nextMonthEmi);
        } catch (Exception e) {
            System.out.println("Inside addEmi() in EmiServiceImpl at line no 50: " + e.getMessage());
        }
        return emi1;

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

    @Override
    public Emi updateEmi(Emi emi) {
        emi.setModifiedBy(emi.getCreatedBy());
        emi.setModifiedDate(null);

        return null;// this.emiRepository.save(emi);
    }

    @Override
    public Set<Emi> getEmis() {
        return new LinkedHashSet<>(this.emiRepository.findAll());
    }

    @Override
    public Emi getEmi(Long emiId) {
        Emi emi = this.emiRepository.findAllByEid(emiId);
        return emi;
    }

    @Override
    public void deleteEmi(Long emiId) {
        this.emiRepository.deleteById(emiId);

    }

    @Override
    public List<Emi> getEmiOfLoan(Loan loan) {
        // TODO Auto-generated method stub
        return this.emiRepository.findByLoan(loan, Sort.by(Sort.Direction.ASC, "emiDate"));
    }

}
