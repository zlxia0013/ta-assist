package com.tc.ta.core.user.bo;


import com.tc.ta.core.user.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jack on 2017/3/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-core.xml")
public class UserBoTest {

    @Autowired
    private UserBo userBo;

    @Test
    public void testHello() {
        User user = userBo.getById(1);
        System.out.println(user.getName());

    }


}