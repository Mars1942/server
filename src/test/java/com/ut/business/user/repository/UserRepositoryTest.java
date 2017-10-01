package com.ut.business.user.repository;

import com.ut.business.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest{

    @Autowired
    private UserRepository userRepository = null;


    @Test
    public void testFindByName() {
        List<User> userList = userRepository.findByName("张三");
        for (User user : userList) {
            System.out.println("id:"+user.getId() + "name:" + user.getName());
        }
    }

}
