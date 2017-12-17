package com.ut.business.user.repository;

import com.ut.business.user.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, String>,JpaSpecificationExecutor {

    User findByLoginNameAndPassWord(String loginName, String passWord);
}
