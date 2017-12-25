package com.ut.business.user.service;

import com.ut.business.course.domain.UserToCourse;
import com.ut.business.role.domain.Role;
import com.ut.business.role.domain.UserToRole;
import com.ut.business.role.repository.UserToRoleRepository;
import com.ut.business.user.controller.UserVo;
import com.ut.business.user.domain.User;
import com.ut.business.user.repository.UserPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserPagingAndSortingRepository userPagingAndSortingRepository;

    @Autowired
    private UserToRoleRepository userToRoleRepository;

    @Transactional
    public String save(User user,List<String> roleIds) throws Exception {
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
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
        Date now = new Date();
        user.setUpdateTime(now);
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

    public Page<User> search(final UserVo userVo, final int pageNumber, final int pageSize) throws Exception{
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                // 设置sql链接
                Join<User, UserToRole> join = root.join("uToRList", JoinType.LEFT);
                if (userVo.getCourseId() != null && !userVo.getCourseId().equals("")) {
                    Join<User,UserToCourse> rJoin = root.join("uTocList", JoinType.LEFT);
                    predicate.getExpressions().add(cb.equal(rJoin.get("course").get("id"), userVo.getCourseId()));
                }
                if (userVo.getRoleCode() != null && !userVo.getRoleCode().equals("")) {
                    predicate.getExpressions().add(cb.equal(join.get("role").get("code"), userVo.getRoleCode()));
                }
                if (userVo.getName() != null && !userVo.getName().equals("")) {
                    predicate.getExpressions().add(cb.like(root.<String>get("name"), userVo.getName() + "%"));
                }
                return predicate;
            }
        };
        Pageable pageable = new PageRequest(pageNumber,pageSize, Sort.Direction.DESC,"createTime");
        return userPagingAndSortingRepository.findAll(spec,pageable);
    }

    public List<User> search(final UserVo userVo) throws Exception{
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                // 设置sql链接
                Join<User, UserToRole> join = root.join("uToRList", JoinType.LEFT);
                if (userVo.getCourseId() != null && !userVo.getCourseId().equals("")) {
                    Join<User,UserToCourse> rJoin = root.join("uTocList", JoinType.LEFT);
                    predicate.getExpressions().add(cb.equal(rJoin.get("course").get("id"), userVo.getCourseId()));
                }
                if (userVo.getRoleCode() != null && !userVo.getRoleCode().equals("")) {
                    predicate.getExpressions().add(cb.equal(join.get("role").get("code"), userVo.getRoleCode()));
                }
                if (userVo.getIds() != null && !userVo.getIds().isEmpty()) {
                    predicate.getExpressions().add(cb.notEqual(root.get("id"), userVo.getIds()));
                }
                if (userVo.getName() != null && !userVo.getName().equals("")) {
                    predicate.getExpressions().add(cb.like(root.<String>get("name"), userVo.getName() + "%"));
                }
                // 或
//                Predicate p1 = cb.equal(rJoin.get("code").as(String.class), code);
//                query.where(cb.and(p1));
//                return query.getRestriction();
                return predicate;
            }
        };
        return userPagingAndSortingRepository.findAll(spec);
    }

    public List<User> ListAll() {
        return (List<User>) userPagingAndSortingRepository.findAll();
    }

    public User findByLoginNameAndPassWord(String name, String passWord) throws Exception{
        return userPagingAndSortingRepository.findByLoginNameAndPassWord(name, passWord);
    }

    public List<User> findByIdNotIn(List<String> ids) {
        return userPagingAndSortingRepository.findByIdNotIn(ids);
    }
}
