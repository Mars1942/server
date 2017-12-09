package com.ut.business.user.service;

import com.ut.business.user.domain.User;
import com.ut.business.user.repository.UserPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserPagingAndSortingRepository userPagingAndSortingRepository;

    @Transactional
    public String save(User user) throws Exception {
        return userPagingAndSortingRepository.save(user).getId();
    }

    @Transactional
    public void del(String id) throws Exception {
        userPagingAndSortingRepository.delete(id);
    }

    public User findById(String id)  throws Exception {
        return userPagingAndSortingRepository.findOne(id);
    }

    public Page<User> findAll(int pageNumber, int pageSize) throws Exception{
        Sort sort = new Sort("name");
        PageRequest request = new PageRequest(pageNumber,pageSize,sort);
        return userPagingAndSortingRepository.findAll(request);
    }

    public User findByLoginNameAndPassWord(String name, String passWord) throws Exception{
        return userPagingAndSortingRepository.findByLoginNameAndPassWord(name, passWord);
    }
}
