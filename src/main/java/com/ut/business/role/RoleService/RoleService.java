package com.ut.business.role.RoleService;

import com.ut.business.role.domain.Role;
import com.ut.business.role.domain.UserToRole;
import com.ut.business.role.repository.RoleRepository;
import com.ut.business.role.repository.UserToRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository RoleRepository;

    @Autowired
    private UserToRoleRepository userToRoleRepository;

    @Transactional
    public String save(Role role) throws Exception{ return RoleRepository.save(role).getId();}

    public Page<Role> findAll(int pageNumber, int pageSize) throws Exception{
        Sort sort = new Sort("name");
        PageRequest request = new PageRequest(pageNumber,pageSize,sort);
        return RoleRepository.findAll(request);
    }

    public Iterable<Role> findAll() throws Exception{
        Sort sort = new Sort("name");
        return RoleRepository.findAll(sort);
    }

    public Role findById(String id) throws Exception{
        return RoleRepository.findOne(id);
    }

    @Transactional
    public String save(UserToRole userToRole) throws Exception{
        return userToRoleRepository.save(userToRole).getId();
    }

    @Transactional
    public void del(String id) throws Exception {
        RoleRepository.delete(id);
        List<UserToRole> list = userToRoleRepository.findByRoleId(id);
        for (UserToRole userToRole : list) {
            userToRoleRepository.delete(userToRole);
        }
    }
}
