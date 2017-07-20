package com.tc.ta.core.privilege.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import com.tc.ta.core.privilege.dto.PrivilegeDto;
import com.tc.ta.core.privilege.dto.PrivilegeSearchCondition;
import com.tc.ta.core.privilege.pojo.Privilege;

public interface PrivilegeDao {
    Privilege getById(@Param("id") int id);

    void add(Privilege privilege);

    void update(Privilege privilege);

    void delete(Integer privilegeId);

    List<Privilege> searchPojo(PrivilegeSearchCondition sc);

    List<PrivilegeDto> searchDto(PrivilegeSearchCondition sc);

    Integer searchCnt(PrivilegeSearchCondition sc);

//======================================= new method create here ====================================/

    List<Privilege> searchPojoByRoleCode(String roleCode);

}
