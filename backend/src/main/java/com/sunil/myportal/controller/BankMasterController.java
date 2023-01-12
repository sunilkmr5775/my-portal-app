package com.sunil.myportal.controller;

import java.util.ArrayList;
import java.util.List;

import com.sunil.myportal.config.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.myportal.model.BankMaster;
import com.sunil.myportal.service.BankMasterService;

@RestController
@Component
@RequestMapping("/banks")
@CrossOrigin("*")
public class BankMasterController {


    @Autowired
    private BankMasterService bankMasterService;

    //	 GET ALL CATEGORIES
    @GetMapping("/")
    public List<BankMaster> getAllBanks() {
        return new ArrayList<>(this.bankMasterService.getAllBanks());

    }

    //	 GET SINGLE Bank BY ID
    @GetMapping("/{bankId}")
    public BankMaster getBankById(@PathVariable("bankId") Long bankId) {
        return this.bankMasterService.getBank(bankId);

    }

    //	 DELETE CATEGORY BY ID
    @DeleteMapping("/{bankId}")
    public void deleteBank(@PathVariable Long bankId) {
        this.bankMasterService.deleteBank(bankId);

    }

    @GetMapping("/sort")
    public List<BankMaster> sortByBankName(@RequestParam String direction) {
        return this.bankMasterService.sortByBankName(direction);
    }

    //	 ADD SYSTEM INFO
    @GetMapping("/system-info")
    public String testApi() {
        SystemInfo si = new SystemInfo();
        return si.diskInfo();

    }
}
