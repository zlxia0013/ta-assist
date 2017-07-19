package com.tc.ta.core.role.bo;


import com.tc.ta.core.role.pojo.Role;
import com.tc.ta.core.user.bo.UserBo;
import com.tc.ta.core.user.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Jack on 2017/3/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-core.xml")
public class RoleBoTest {

    @Autowired
    private RoleBo roleBo;

    @Test
    public void testHello() {
        Role role = roleBo.getById(1);
        System.out.println(role.getCode());

    }


}