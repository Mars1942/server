package com.ut.business.user.service;

import com.ut.business.role.domain.Role;
import com.ut.business.role.domain.UserToRole;
import com.ut.business.role.repository.UserToRoleRepository;
import com.ut.business.user.domain.User;
import com.ut.business.user.repository.UserPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserPagingAndSortingRepository userPagingAndSortingRepository;

    @Autowired
    private UserToRoleRepository userToRoleRepository;

    @Transactional
    public String save(User user,List<String> roleIds) throws Exception {
        String id = userPagingAndSortingRepository.save(user).getId();
        if (roleIds != null) {
            for (String roleId: roleIds) {
                UserToRole userToRole = new UserToRole();
                user.setId(id);
                userToRole.setUser(user);
                Role role = new Role();
                role.setId(roleId);
                userToRole.setRole(role);
                userToRoleRepository.save(userToRole);
            }
        }
        return id;
    }

    @Transactional
    public String update(User user, List<String> roleIds) {
        userPagingAndSortingRepository.save(user);
        List<UserToRole> uTorList = userToRoleRepository.findByUserId(user.getId());
        for (UserToRole userToRole: uTorList) {
            userToRoleRepository.delete(userToRole);
        }
        if (roleIds != null) {
            for (String roleId: roleIds) {
                UserToRole userToRole = new UserToRole();
                userToRole.setUser(user);
                Role role = new Role();
                role.setId(roleId);
                userToRole.setRole(role);
                userToRoleRepository.save(userToRole);
            }
        }
        return user.getId();
    }

    @Transactional
    public void del(String id) throws Exception {
        userPagingAndSortingRepository.delete(id);
       /* List<UserToRole> uTorList = userToRoleRepository.findByUserId(id);
        for (UserToRole userToRole: uTorList) {
            userToRoleRepository.delete(userToRole);
        }*/
    }

    public User findById(String id)  throws Exception {
        return userPagingAndSortingRepository.findOne(id);
    }

    public Page<User> findAll(int pageNumber, int pageSize) throws Exception{
        Sort sort = new Sort("name");
        PageRequest request = new PageRequest(pageNumber,pageSize,sort);
        return userPagingAndSortingRepository.findAll(request);
    }

    public List<User> ListAll() {
        return (List<User>) userPagingAndSortingRepository.findAll();
    }

    public User findByLoginNameAndPassWord(String name, String passWord) throws Exception{
        return userPagingAndSortingRepository.findByLoginNameAndPassWord(name, passWord);
    }

}
