package com.sunil.myportal.dto;

public class LoanResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private String loanNo;

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	@Override
	public String toString() {
		return "LoanResponse [loanNo=" + loanNo + "]";
	}

}
