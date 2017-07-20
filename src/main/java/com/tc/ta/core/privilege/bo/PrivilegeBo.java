package com.tc.ta.core.privilege.bo;

import com.tc.ta.core.privilege.CheckPrivilegeResultEnum;
import com.tc.ta.core.role.bo.RoleBo;
import com.tc.ta.core.role.pojo.Role;
import com.tc.ta.core.user.pojo.User;
import com.tc.ta.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

import com.tc.ta.util.exception.ComRuntimeException;
import com.tc.ta.core.privilege.bo.PrivilegeBo;
import com.tc.ta.core.privilege.dao.PrivilegeDao;
import com.tc.ta.core.privilege.dto.*;
import com.tc.ta.core.privilege.pojo.Privilege;

import javax.annotation.PostConstruct;

@Service
public class PrivilegeBo {
    @Autowired
    private PrivilegeDao privilegeDao;

    @Autowired
    private RoleBo roleBo;

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
    public List<Privilege> searchPojoByNoLimitAccess(Integer noLimitAccess) {
        PrivilegeSearchCondition sc = new PrivilegeSearchCondition();
        sc.setNoLimitAccess(noLimitAccess);
        return searchPojo(sc);
    }

    public List<Privilege> searchPojoByRoleCode(String roleCode) {
        return privilegeDao.searchPojoByRoleCode(roleCode);
    }


    //======================================= cache privilege start ====================================/
    private Set<String> noLimitAccessPrivileges = new HashSet<String>();
    private Set<String> needLoginPrivileges = new HashSet<String>();
    private Map<String, Set<String>> rolePrivileges = new HashMap<String, Set<String>>();

    @PostConstruct
    public void fillPrivilegeCache() {
        synchronized (rolePrivileges) {
            //for all authorities
            List<Privilege> noLimitAccessPrivilegesLst = searchPojoByNoLimitAccess(Consts.YES_INT);
            for (Privilege privilege : noLimitAccessPrivilegesLst) {
                noLimitAccessPrivileges.add(privilege.getValue());

                if (privilege.getNeedLogin() == Consts.YES_INT) {
                    needLoginPrivileges.add(privilege.getValue());
                }
            }

            //role authorities
            List<Role> allRoles = roleBo.searchAllRoles();
            for (Role role : allRoles) {
                List<Privilege> privilegeList = searchPojoByRoleCode(role.getCode());
                Set<String> privilegeSet = new HashSet<String>();
                for (Privilege privilege : privilegeList) {
                    privilegeSet.add(privilege.getValue());
                }
                rolePrivileges.put(role.getCode(), privilegeSet);
            }
        }
    }

    //刷新缓存， 1分钟
    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void refreshAuthorityCache() {
        fillPrivilegeCache();
    }

    //检查权限
    public CheckPrivilegeResultEnum checkPrivilege(User userInfo, String url) {
        //管理员具有所有的权限
        if (userInfo != null && userInfo.isAdmin()) {
            return CheckPrivilegeResultEnum.RETURN_TRUE;
        }

        //无条件跳转
        if (noLimitAccessPrivileges.contains(url)) {
            if (needLoginPrivileges.contains(url) && userInfo == null) {
                return CheckPrivilegeResultEnum.NEED_LOGIN;
            } else {
                return CheckPrivilegeResultEnum.RETURN_TRUE;
            }
        }

        if (userInfo != null) {
            for (Role role : userInfo.getRoleList()) {
                if (rolePrivileges.get(role.getCode()).contains(url)) {
                    return CheckPrivilegeResultEnum.RETURN_TRUE;
                }
            }
            return CheckPrivilegeResultEnum.NO_AUTHORITY;
        } else {
            return CheckPrivilegeResultEnum.NEED_LOGIN;
        }
    }
    //======================================= cache privilege end ====================================/


}
