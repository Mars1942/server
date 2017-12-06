package com.ut.business.user.service;

import com.ut.business.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest{

    @Autowired
    private UserService userService = null;

    @Test
    public void testSave(){
        userService.save(new User("张三",10));
    }

    @Test
    public void testPuery(){
        User user = userService.findByLoginNameAndPassWord("1","1");
        System.out.println("name" + user.getLoginName());
    }
}
