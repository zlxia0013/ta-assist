package com.tc.ta.core.role.pojo;

import java.util.Date;
import com.tc.ta.util.framework.BaseSearchCondition;

public class Role  extends BaseSearchCondition{
	private Integer id;

	private String code;

	private String label;
	
	// %%ONLY_FOR_INS_FLD%FI_JAVA_ENTITY_DECLARE%%

	public Role() {		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	// %%ONLY_FOR_INS_FLD%FI_JAVA_ENTITY_GET_SET%%
}
