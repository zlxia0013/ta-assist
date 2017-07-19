package com.tc.ta.core.role.dao;

import java.util.*;
import org.apache.ibatis.annotations.Param;
import com.tc.ta.core.role.dto.RoleDto;
import com.tc.ta.core.role.dto.RoleSearchCondition;
import com.tc.ta.core.role.pojo.Role;

public interface RoleDao {
		Role getById(@Param("id") int id);

		void add(Role role);

		void update(Role role);

		void delete(Integer roleId);

		List<Role> searchPojo(RoleSearchCondition sc);

		List<RoleDto> searchDto(RoleSearchCondition sc);

		Integer searchCnt(RoleSearchCondition sc);

//======================================= new method create here ====================================/


	//%%NPT%JAVA_ENTITY_DAO_METHOD_INSERT%%
}
