package com.ut.business.role.RoleService;

import com.ut.business.role.domain.Role;
import com.ut.business.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;

public class RoleService {

    @Autowired
    private RoleRepository RoleRepository;

    @Transactional
    public String save(Role role) throws Exception{ return RoleRepository.save(role).getId();}

    public Page<Role> findAll(int pageNumber, int pageSize) throws Exception{
        Sort sort = new Sort("name");
        PageRequest request = new PageRequest(pageNumber,pageSize,sort);
        return RoleRepository.findAll(request);
    }
}
