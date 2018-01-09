package com.ut.business.application.repository;

import com.ut.business.application.domain.Application;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationPagingAndSortingRepository extends PagingAndSortingRepository<Application, String>, JpaSpecificationExecutor {
}
