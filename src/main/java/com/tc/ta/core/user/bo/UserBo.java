package com.tc.ta.core.user.bo;


import com.tc.ta.core.user.SuperUserName;
import com.tc.ta.core.user.UserStateEnum;
import com.tc.ta.core.user.dao.UserDao;
import com.tc.ta.core.user.dto.UserDto;
import com.tc.ta.core.user.dto.UserSearchCondition;
import com.tc.ta.core.user.pojo.User;
import com.tc.ta.util.StringUtil;
import com.tc.ta.util.exception.ComRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserBo {
    @Autowired
    private UserDao userDao;

    public User getById(Integer id) {
        return userDao.getById(id);
    }

    public void add(User user) {
        validateAddUpdate(user, true);
        userDao.add(user);
    }

    public void update(User user) {
        validateAddUpdate(user, false);
        userDao.update(user);
    }

    private void validateAddUpdate(User user, boolean isAdd) {
        if (user == null) {
            throw new ComRuntimeException("用户实体不能为空");
        }

        if (StringUtil.isEmpty(user.getName())) {
            throw new ComRuntimeException("用户名不能为空");
        } else if (user.getName().length() > 20) {
            throw new ComRuntimeException("用户名不能大于20位");
        }

        if (StringUtil.isEmpty(user.getFullName())) {
            throw new ComRuntimeException("姓名不能为空");
        } else if (user.getFullName().length() > 15) {
            throw new ComRuntimeException("姓名不能大于15位");
        }

        if (StringUtil.isEmpty(user.getPwd())) {
            throw new ComRuntimeException("密码不能为空");
        } else if (user.getPwd().length() > 20) {
            throw new ComRuntimeException("密码不能大于20位");
        }

        if (!UserStateEnum.isValid(user.getStateId())) {
            throw new ComRuntimeException("状态无效");
        }

        if (!StringUtil.isEmpty(user.getRemark()) && user.getRemark().length() > 100) {
            throw new ComRuntimeException("备注不能大于100位");
        }

        //////////////////
        User user1 = getByUserName(user.getName());
        if (isAdd) {
            if (user1 != null) {
                throw new ComRuntimeException("用户名已存在，请重新输入");
            }
        } else {
            if (user.getId() == null) {
                throw new ComRuntimeException("用户id不能为空");
            }

            if (user1 != null && user1.getId() != user.getId().intValue()) {
                throw new ComRuntimeException("用户名已存在，请重新输入");
            }
        }
    }

    public void delete(Integer userId) {
        User user = getById(userId);
        if (user == null) {
            return;
        }

        if (SuperUserName.ADMIN.equals(user.getName())) {
            throw new ComRuntimeException("不能删除超级管理员");
        }

        userDao.delete(userId);
    }

    public List<User> searchPojo(UserSearchCondition sc) {
        return userDao.searchPojo(sc);
    }

    public List<UserDto> searchDto(UserSearchCondition sc) {
        return userDao.searchDto(sc);
    }

    public Integer searchCnt(UserSearchCondition sc) {
        return userDao.searchCnt(sc);
    }

    //======================================= new method create here ====================================/
    public User getByUserName(String userName) {
        return userDao.getByUserName(userName);
    }

    public User login(String userName, String password) {
        User user = getByUserName(userName);

        if (user == null) {
            throw new ComRuntimeException("用户名不存在");
        }

        if (!user.getPwd().equals(password)) {
            throw new ComRuntimeException("密码错误");
        }

        if (!UserStateEnum.enabled(user.getStateId())) {
            throw new ComRuntimeException("您的帐户已被禁用");
        }

        return user;
    }


    public void updatePwd(String userName, String oldPwd, String newPwd) {
        User user = getByUserName(userName);
        if (user == null) {
            throw new ComRuntimeException("用户名不存在");
        }

        if (!user.getPwd().equals(oldPwd)) {
            throw new ComRuntimeException("原密码错误");
        }

        if (StringUtil.isEmpty(newPwd)) {
            throw new ComRuntimeException("新密码不能为空");
        } else if (newPwd.length() > 20) {
            throw new ComRuntimeException("新密码不能大于20位");
        }

        //direct update
        user.setPwd(newPwd);
        userDao.update(user);
    }

}
