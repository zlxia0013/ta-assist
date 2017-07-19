package com.tc.ta.core.user.dao;


import com.tc.ta.core.user.dto.UserDto;
import com.tc.ta.core.user.dto.UserSearchCondition;
import com.tc.ta.core.user.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    User getById(@Param("id") int id);

    void add(User user);

    void update(User user);

    void delete(Integer userId);

    List<User> searchPojo(UserSearchCondition sc);

    List<UserDto> searchDto(UserSearchCondition sc);

    Integer searchCnt(UserSearchCondition sc);

    //======================================= new method create here ====================================/
    User getByUserName(String userName);
}
