package com.tc.ta.core.privilege.bo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.tc.ta.util.exception.ComRuntimeException;
import com.tc.ta.core.privilege.bo.PrivilegeBo;
import com.tc.ta.core.privilege.dao.PrivilegeDao;
import com.tc.ta.core.privilege.dto.*;
import com.tc.ta.core.privilege.pojo.Privilege;

@Service
public class PrivilegeBo {
    @Autowired
    private PrivilegeDao privilegeDao;

    public Privilege getById(Integer id) {
		return privilegeDao.getById(id);
    }

    public void add(Privilege privilege) {
		validateAddUpdate(privilege, true);
		privilegeDao.add(privilege);
    }

    public void update(Privilege privilege) {
		validateAddUpdate(privilege, false);
		privilegeDao.update(privilege);
    }

    private void validateAddUpdate(Privilege privilege, boolean isAdd) {
		if (privilege == null) {
		    throw new ComRuntimeException("对象不能为空");
        }
    }

    public void delete(Integer privilegeId) {
		privilegeDao.delete(privilegeId);
    }

    public List<Privilege> searchPojo(PrivilegeSearchCondition sc) {
		return privilegeDao.searchPojo(sc);
    }

    public List<PrivilegeDto> searchDto(PrivilegeSearchCondition sc) {
		return privilegeDao.searchDto(sc);
    }

    public Integer searchCnt(PrivilegeSearchCondition sc) {
		return privilegeDao.searchCnt(sc);
    }

		//======================================= new method create here ====================================/
}
