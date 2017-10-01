package com.ut.business.user.service;

import com.ut.business.user.domain.User;
import com.ut.business.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }

}
