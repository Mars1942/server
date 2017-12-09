package com.ut.business.role.repository;

import com.ut.business.role.domain.UserToRole;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserToRoleRepository extends PagingAndSortingRepository<UserToRole, String> {
}
