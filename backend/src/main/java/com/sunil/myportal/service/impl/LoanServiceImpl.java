package com.sunil.myportal.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.sunil.myportal.exception.InvalidDatabaseConnectionException;
import com.sunil.myportal.model.BankMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunil.myportal.constant.ExceptionConstant;
import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.LoanRequest;
import com.sunil.myportal.dto.LoanResponse;
import com.sunil.myportal.model.Loan;
import com.sunil.myportal.model.LoanTypes;
import com.sunil.myportal.repository.LoanRepository;
import com.sunil.myportal.repository.LoanTypeRepository;
import com.sunil.myportal.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanTypeRepository loanTypeRepository;

//	@Autowired
//	private EmiRepository emiRepository;

    @Override
    public LoanResponse addNewLoan(LoanRequest loanRequest)
            throws UnsupportedOperationException, URISyntaxException, IOException {

        LoanResponse loanResponse = new LoanResponse();
        Loan loan = new Loan();
        Long loanId = 0L;
        String bankId = loanRequest.getBank();
        try {

            loan.setLoanNo(loanRequest.getLoanNo());
            loan.setLoanType(loanRequest.getLoanType());
            loan.setDisbursalDate(loanRequest.getDisbursalDate());
            loan.setEmiAmount(loanRequest.getEmiAmount() != null ? loanRequest.getEmiAmount() : new BigDecimal("0.0"));
            loan.setEmiPaid(loanRequest.getEmiPaid());
            loan.setEmiRemaining(loanRequest.getEmiRemaining());
            loan.setFirstEmiDate(loanRequest.getFirstEmiDate());
            loan.setInterestPaid(
                    loanRequest.getInterestPaid() != null ? loanRequest.getInterestPaid() : new BigDecimal("0.0"));
            loan.setInterestType(loanRequest.getInterestType());
            loan.setLastEmiDate(loanRequest.getLastEmiDate());
            loan.setLoanAmount(

                    loanRequest.getLoanAmount() != null ? loanRequest.getLoanAmount() : new BigDecimal("0.0"));

            BankMaster bm = new BankMaster();

//			loan.setBankMaster(loanRequest.getBank());
            loan.setInterestRate(loanRequest.getInterestRate());
            loan.setLoanStatus(true);
            loan.setTotalEmi(loanRequest.getTotalEmi());
            loan.setStatus(StatusConstant.STATUS_ACTIVE);
            loan.setCreatedBy("sunilkmr5775");
            loan.setCreatedDate(LocalDateTime.now());
            loan.setModifiedBy(null);
            loan.setModifiedDate(null);
//            try {
//                loanId = loanRepository.save(loan).getLoanId();
                loanRepository.save(loan);
           /* } catch (SQLException exx) {
                throw new InvalidDatabaseConnectionException("Invalid database credentials provided", new Throwable().getCause());
            }*/
            try {
                if (loanId > 0) {
                    loanResponse.setStatus(StatusConstant.STATUS_SUCCESS);
                    loanResponse.setLoanNo(loanRequest.getLoanNo());
                    loanResponse.setErrorCode(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_EC);
                    loanResponse.setErrorDescription(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_ED);

                    return loanResponse;
                }
            } catch (Exception e) {
                loanResponse.setStatus(StatusConstant.STATUS_FAILURE);
                loanResponse.setLoanNo(loanRequest.getLoanNo());
                loanResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
                loanResponse.setErrorDescription(e.getMessage());
                return loanResponse;
            }
        } catch (Exception ex) {
            loanResponse.setStatus(StatusConstant.STATUS_FAILURE);
            loanResponse.setLoanNo(loanRequest.getLoanNo());
            loanResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
            loanResponse.setErrorDescription(ex.getMessage());
            return loanResponse;

        }
        return loanResponse;
    }

    public Loan updateLoan(Loan loan) {
        return this.loanRepository.save(loan);
    }

    @Override
    public List<Loan> getAllLoans() {
        return new ArrayList<>(this.loanRepository.findAllByLoanStatus(true));
//		return new ArrayList<>(this.loanRepository.findAllByLoanStatus(Sort.by(Sort.Direction.ASC, "bankName")));
    }

    @Override
    public List<Loan> getAllActiveLoans() {
        return new ArrayList<>(this.loanRepository.findByStatus(StatusConstant.STATUS_ACTIVE));
    }

    @Override
    public Loan getLoan(Long loanId) {
        Loan loan = this.loanRepository.findById(loanId).get();
        return loan;
    }

    @Override
    public void deleteLoan(Long loanId) {
        /*
         * Loan loan = new Loan(); loan.setLoanId(loanId);
         * this.loanRepository.delete(loan);
         */

        Loan loan = this.loanRepository.findById(loanId).get();
        loan.setLoanStatus(false);
        loan.setStatus(StatusConstant.STATUS_CLOSED);
        this.loanRepository.save(loan);

    }

    @Override
    public void deleteByLoanNo(String loanNo) {
        Loan loan = new Loan();
        loan.setLoanNo(loanNo);
        this.loanRepository.deleteByLoanNo(loan);

    }

    @Override
    public Set<Loan> getLoansByStatus(String loanStatus) {
        if ("ALL".equalsIgnoreCase(loanStatus)) {
            return new LinkedHashSet<>(this.loanRepository.findAllByOrderByStatusAsc());
        } else {
            return new LinkedHashSet<>(this.loanRepository.findByStatus(loanStatus));
        }
    }

    @Override
    public List<Loan> findAllLoanStoredProcedure(String loanNo, String loanStatus, String bankName) {
        // TODO Auto-generated method stub
        ArrayList<Loan> result = this.loanRepository.findAllLoanStoredProcedure(loanNo, loanStatus, bankName);
        return result;
    }

    @Override
    public List<LoanTypes> getAllLoanTypes() {

        return new ArrayList<>(loanTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "loanType")));
    }

}
