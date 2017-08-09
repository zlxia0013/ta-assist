package com.tc.ta.core.fundcodechange.pojo;

import java.util.Date;
import com.tc.ta.util.framework.BaseSearchCondition;

public class FundCodeChange  extends BaseSearchCondition{
	private String fundCode;

	private String shareClass;

	private String outerFundCode;

	private String outerFundName;
	
	// %%ONLY_FOR_INS_FLD%FI_JAVA_ENTITY_DECLARE%%

	public FundCodeChange() {		
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getShareClass() {
		return shareClass;
	}

	public void setShareClass(String shareClass) {
		this.shareClass = shareClass;
	}

	public String getOuterFundCode() {
		return outerFundCode;
	}

	public void setOuterFundCode(String outerFundCode) {
		this.outerFundCode = outerFundCode;
	}

	public String getOuterFundName() {
		return outerFundName;
	}

	public void setOuterFundName(String outerFundName) {
		this.outerFundName = outerFundName;
	}
	
	// %%ONLY_FOR_INS_FLD%FI_JAVA_ENTITY_GET_SET%%
}
