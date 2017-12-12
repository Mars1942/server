package com.ut.business.application;

import com.ut.business.application.domain.Application;
import com.ut.business.application.service.ApplicationService;
import com.ut.business.role.RoleService.RoleService;
import com.ut.business.role.domain.UserToRole;
import com.ut.business.user.domain.User;
import com.ut.business.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationServiceTest {

    @Autowired
    private ApplicationService applicationService = null;

    @Autowired
    private RoleService roleService = null;

    @Test
    public void testFindAll() throws Exception {
//        userService.save(new User("张三",10));
        Page<Application> appList = applicationService.findAll(0,999);
    }

    @Test
    public void testPuery(){
//        User user = userService.findByLoginNameAndPassWord("1","1");
//        System.out.println("name" + user.getLoginName());
    }

}
