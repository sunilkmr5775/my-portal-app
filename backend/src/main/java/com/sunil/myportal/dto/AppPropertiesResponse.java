package com.sunil.myportal.dto;

import java.time.LocalDateTime;

public class AppPropertiesResponse extends BaseResponse{

	public AppPropertiesResponse() {
//		super();
		// TODO Auto-generated constructor stub
	}

	private String attributeType;
	private String ruleKey;
	private String ruleValue;
	private String status;
	private String createdBy;
	private LocalDateTime createdDate;

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public String getRuleKey() {
		return ruleKey;
	}

	public void setRuleKey(String ruleKey) {
		this.ruleKey = ruleKey;
	}

	public String getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AppPropertiesResponse [attributeType=" + attributeType + ", ruleKey=" + ruleKey + ", ruleValue="
				+ ruleValue + ", status=" + status + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
	}

}
