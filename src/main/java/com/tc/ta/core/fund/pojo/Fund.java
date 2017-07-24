package com.tc.ta.core.fund.pojo;

import java.util.Date;
import com.tc.ta.util.framework.BaseSearchCondition;

public class Fund  extends BaseSearchCondition{
	private String fundCode;

	private String fundName;

	private String fundEnglishName;

	private String fundClass;

	private String fundType;

	private String shareClass;

	private String currencyType;

	private String fundManagerCode;

	private String custodianCode;

	private String faCode;

	private String managerFeeRate;

	private String specification;

	private String fundStatus;

	private String isGuaranteedFund;

	private String isStructuredFund;

	private String isMultiFund;

	private String isQdiiFund;

	private String isEtfFund;

	private String isLof;

	private String taCode;

	private String isSync;

	private String refFund;

	private Integer addUserId;

	private Date addTime;
	
	// %%ONLY_FOR_INS_FLD%FI_JAVA_ENTITY_DECLARE%%

	public Fund() {		
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFundEnglishName() {
		return fundEnglishName;
	}

	public void setFundEnglishName(String fundEnglishName) {
		this.fundEnglishName = fundEnglishName;
	}

	public String getFundClass() {
		return fundClass;
	}

	public void setFundClass(String fundClass) {
		this.fundClass = fundClass;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getShareClass() {
		return shareClass;
	}

	public void setShareClass(String shareClass) {
		this.shareClass = shareClass;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getFundManagerCode() {
		return fundManagerCode;
	}

	public void setFundManagerCode(String fundManagerCode) {
		this.fundManagerCode = fundManagerCode;
	}

	public String getCustodianCode() {
		return custodianCode;
	}

	public void setCustodianCode(String custodianCode) {
		this.custodianCode = custodianCode;
	}

	public String getFaCode() {
		return faCode;
	}

	public void setFaCode(String faCode) {
		this.faCode = faCode;
	}

	public String getManagerFeeRate() {
		return managerFeeRate;
	}

	public void setManagerFeeRate(String managerFeeRate) {
		this.managerFeeRate = managerFeeRate;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getFundStatus() {
		return fundStatus;
	}

	public void setFundStatus(String fundStatus) {
		this.fundStatus = fundStatus;
	}

	public String getIsGuaranteedFund() {
		return isGuaranteedFund;
	}

	public void setIsGuaranteedFund(String isGuaranteedFund) {
		this.isGuaranteedFund = isGuaranteedFund;
	}

	public String getIsStructuredFund() {
		return isStructuredFund;
	}

	public void setIsStructuredFund(String isStructuredFund) {
		this.isStructuredFund = isStructuredFund;
	}

	public String getIsMultiFund() {
		return isMultiFund;
	}

	public void setIsMultiFund(String isMultiFund) {
		this.isMultiFund = isMultiFund;
	}

	public String getIsQdiiFund() {
		return isQdiiFund;
	}

	public void setIsQdiiFund(String isQdiiFund) {
		this.isQdiiFund = isQdiiFund;
	}

	public String getIsEtfFund() {
		return isEtfFund;
	}

	public void setIsEtfFund(String isEtfFund) {
		this.isEtfFund = isEtfFund;
	}

	public String getIsLof() {
		return isLof;
	}

	public void setIsLof(String isLof) {
		this.isLof = isLof;
	}

	public String getTaCode() {
		return taCode;
	}

	public void setTaCode(String taCode) {
		this.taCode = taCode;
	}

	public String getIsSync() {
		return isSync;
	}

	public void setIsSync(String isSync) {
		this.isSync = isSync;
	}

	public String getRefFund() {
		return refFund;
	}

	public void setRefFund(String refFund) {
		this.refFund = refFund;
	}

	public Integer getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	// %%ONLY_FOR_INS_FLD%FI_JAVA_ENTITY_GET_SET%%
}
