package com.ut.business.role.RoleService;

import com.ut.business.application.domain.Application;
import com.ut.business.application.domain.RoleToApplication;
import com.ut.business.application.repository.RoleToApplicationRepository;
import com.ut.business.role.domain.Role;
import com.ut.business.role.domain.UserToRole;
import com.ut.business.role.repository.RoleRepository;
import com.ut.business.role.repository.UserToRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository RoleRepository;

    @Autowired
    private UserToRoleRepository userToRoleRepository;

    @Autowired
    private RoleToApplicationRepository roleToApplicationRepository;

    @Transactional
    public String save(Role role, List<String> applicationIds) throws Exception{
        String id = RoleRepository.save(role).getId();
        if (applicationIds != null) {
            for (String appId:applicationIds) {
                RoleToApplication roleToApplication = new RoleToApplication();
                Application application = new Application();
                application.setId(appId);
                roleToApplication.setApplication(application);
                role.setId(id);
                roleToApplication.setRole(role);
                roleToApplicationRepository.save(roleToApplication);
            }
        }
        return id;
    }

    @Transactional
    public String update(Role role, List<String> applicationIds) {
        RoleRepository.save(role);
        List<RoleToApplication> rToAList = roleToApplicationRepository.findByRoleId(role.getId());
        for (RoleToApplication roleToApplication:rToAList) {
            roleToApplicationRepository.delete(roleToApplication);
        }
        if (applicationIds != null) {
            for (String appId:applicationIds) {
                RoleToApplication roleToApplication = new RoleToApplication();
                Application application = new Application();
                application.setId(appId);
                roleToApplication.setApplication(application);
                roleToApplication.setRole(role);
                roleToApplicationRepository.save(roleToApplication);
            }
        }
        return role.getId();
    }

    public Page<Role> findAll(int pageNumber, int pageSize) throws Exception{
        Sort sort = new Sort("name");
        PageRequest request = new PageRequest(pageNumber,pageSize,sort);
        return RoleRepository.findAll(request);
    }

    public Page<Role> search(final Role role, final int pageNumber, final int pageSize) {
        Specification<Role> spec = new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (role.getName() != null && !role.getName().equals("")) {
                    Predicate p1 = cb.like(root.get("name").as(String.class), role.getName() + "%");
                    query.where(cb.and(p1));
                }
                return query.getRestriction();
            }
        };
        Pageable pageable = new PageRequest(pageNumber,pageSize, Sort.Direction.DESC,"sort");
        return RoleRepository.findAll(spec,pageable);
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
        /*List<UserToRole> list = userToRoleRepository.findByRoleId(id);
        for (UserToRole userToRole : list) {
            userToRoleRepository.delete(userToRole);
        }*/
    }

}
