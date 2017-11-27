package com.ut.business.role.repository;

import com.ut.business.role.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, String> {
}
