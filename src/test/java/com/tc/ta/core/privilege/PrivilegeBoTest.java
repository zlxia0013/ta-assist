package com.tc.ta.core.privilege;


import com.tc.ta.core.privilege.bo.PrivilegeBo;
import com.tc.ta.core.privilege.pojo.Privilege;
import com.tc.ta.core.role.bo.RoleBo;
import com.tc.ta.core.role.pojo.Role;
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
public class PrivilegeBoTest {

    @Autowired
    private PrivilegeBo privilegeBo;

    @Test
    public void testHello() {
        Privilege privilege = privilegeBo.getById(1);
        System.out.println(privilege.getCode());

    }


}