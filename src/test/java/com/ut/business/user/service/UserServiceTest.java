package com.ut.business.user.service;

import com.ut.business.course.domain.Course;
import com.ut.business.course.service.CourseService;
import com.ut.business.role.RoleService.RoleService;
import com.ut.business.role.domain.UserToRole;
import com.ut.business.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest{

    @Autowired
    private UserService userService = null;

    @Autowired
    private RoleService roleService = null;

    @Autowired
    private CourseService courseService = null;

    @Test
    public void testSave(){
//        userService.save(new User("张三",10));
    }

    @Test
    public void testPuery(){
//        User user = userService.findByLoginNameAndPassWord("1","1");
//        System.out.println("name" + user.getLoginName());
    }

    @Test
    public void testUserToRole() throws Exception {
        User user = new User();
        user.setId("1");
        UserToRole utr = new UserToRole();
        utr.setUser(user);
        roleService.save(utr);
    }

    @Test
    public void testToCourse() throws Exception {
        Course course = new Course();
        course.setCount(3);
//        courseService.save(course);
    }
}
