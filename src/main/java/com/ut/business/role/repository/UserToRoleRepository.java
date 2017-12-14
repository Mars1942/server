package com.ut.business.role.repository;

import com.ut.business.role.domain.UserToRole;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserToRoleRepository extends PagingAndSortingRepository<UserToRole, String> {

    public List<UserToRole> findByRoleId(String id);

    public List<UserToRole> findByUserId(String userId);
}
