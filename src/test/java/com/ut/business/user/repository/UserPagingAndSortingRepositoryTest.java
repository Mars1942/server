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
public class UserPagingAndSortingRepositoryTest {

    @Autowired
    private UserPagingAndSortingRepository userRepository = null;


}
