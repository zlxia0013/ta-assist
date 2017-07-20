package com.tc.ta.core.role.dto;

import com.tc.ta.core.role.pojo.Role;

public class RoleSearchCondition extends Role {
	private Integer userId;

	public RoleSearchCondition() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
