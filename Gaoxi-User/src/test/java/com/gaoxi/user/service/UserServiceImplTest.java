package com.gaoxi.user.service;

import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.user.UserTypeEnum;
import com.gaoxi.facade.user.UserService;
import com.gaoxi.req.BatchReq;
import com.gaoxi.req.user.*;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void login() throws Exception {
        LoginReq loginReq = new LoginReq();
        loginReq.setUsername("chai");
        loginReq.setPassword("123");
        UserEntity userEntity = userService.login(loginReq);
        System.out.println(userEntity);
    }

    @Test
    public void findUsers() throws Exception {
        UserQueryReq userQueryReq = new UserQueryReq();
        userQueryReq.setUsername("cha");
        userQueryReq.setOrderByRegisterTime(1);
        List<UserEntity> userEntityList = userService.findUsers(userQueryReq);
        System.out.println(userEntityList);
    }
}
