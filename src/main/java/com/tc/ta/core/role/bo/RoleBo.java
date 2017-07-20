package com.tc.ta.core.role.bo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.tc.ta.util.exception.ComRuntimeException;
import com.tc.ta.core.role.bo.RoleBo;
import com.tc.ta.core.role.dao.RoleDao;
import com.tc.ta.core.role.dto.*;
import com.tc.ta.core.role.pojo.Role;

@Service
public class RoleBo {
    @Autowired
    private RoleDao roleDao;

    public Role getById(Integer id) {
		return roleDao.getById(id);
    }

    public void add(Role role) {
		validateAddUpdate(role, true);
		roleDao.add(role);
    }

    public void update(Role role) {
		validateAddUpdate(role, false);
		roleDao.update(role);
    }

    private void validateAddUpdate(Role role, boolean isAdd) {
		if (role == null) {
		    throw new ComRuntimeException("对象不能为空");
        }
    }

    public void delete(Integer roleId) {
		roleDao.delete(roleId);
    }

    public List<Role> searchPojo(RoleSearchCondition sc) {
		return roleDao.searchPojo(sc);
    }

    public List<RoleDto> searchDto(RoleSearchCondition sc) {
		return roleDao.searchDto(sc);
    }

    public Integer searchCnt(RoleSearchCondition sc) {
		return roleDao.searchCnt(sc);
    }

    //======================================= new method create here ====================================/

    public List<Role> searchAllRoles() {
        RoleSearchCondition sc = new RoleSearchCondition();
        return searchPojo(sc);
    }

    public List<Role> searchPojoByUserId(Integer userId){
        return roleDao.searchPojoByUserId(userId);
    }
}
