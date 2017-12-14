package com.ut.business.application.repository;

import com.ut.business.application.domain.RoleToApplication;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleToApplicationRepository extends PagingAndSortingRepository<RoleToApplication, String> {

    public List<RoleToApplication> findByRoleId(String id);
}
