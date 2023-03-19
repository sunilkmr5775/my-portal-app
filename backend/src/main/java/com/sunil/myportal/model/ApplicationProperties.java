package com.sunil.myportal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "APPLICATION_PROPERTIES")
public class ApplicationProperties implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
    @Column(name = "ATTRIBUTE_TYPE")
    private String attributeType;

    @Column(name = "RULE_KEY")
    private String ruleKey;

    @Column(name = "RULE_VALUE")
    private String ruleValue;

    @Column(name = "STATUS")
    private String status;


    @Column(name = "CREATED_BY")
    private String createdBy;

    /// @CreationTimestamp
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;


    public ApplicationProperties() {
        this.setCreatedDate(LocalDateTime.now());
      
    }

    public ApplicationProperties(Long id, String attributeType, String ruleKey, String ruleValue, String status, String createdBy, LocalDateTime createdDate) {

        this.id = id;
        this.attributeType = attributeType;
        this.ruleKey = ruleKey;
        this.ruleValue = ruleValue;
        this.status = status;
//        this.buId = buId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public String getRuleKey() {
		return ruleKey;
	}

	public void setKey(String ruleKey) {
		this.ruleKey = ruleKey;
	}

	public String getRuleValue() {
		return ruleValue;
	}

	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}

	@Override
	public String toString() {
		return "ApplicationPropertiesModel [id=" + id + ", attributeType=" + attributeType + ", ruleKey=" + ruleKey + ", ruleValue="
				+ ruleValue + ", status=" + status + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
	}

   

}
