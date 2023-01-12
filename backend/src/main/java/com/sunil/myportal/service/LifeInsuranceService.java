package com.sunil.myportal.service;

import com.sunil.myportal.dto.LifeInsuranceRequest;
import com.sunil.myportal.dto.LifeInsuranceResponse;
import com.sunil.myportal.dto.LoanResponse;
import com.sunil.myportal.model.LifeInsurance;
import com.sunil.myportal.model.Loan;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface LifeInsuranceService {

    public LifeInsuranceResponse addPolicy(LifeInsuranceRequest lifeInsuranceRequest)  throws UnsupportedOperationException, URISyntaxException, IOException;

    public List<LifeInsurance> getAllPolicies();

    void deletePolicy(Long policyId);

    public List<LifeInsurance> findAllLifeInsurancePoliciesProcedure(String policyNo, String policyStatus, String bankName);

    public List<LifeInsurance> getAllActivePolicies();

    LifeInsurance getPolicy(Long policyId);
}
