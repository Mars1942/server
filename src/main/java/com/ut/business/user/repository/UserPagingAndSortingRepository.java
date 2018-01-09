package com.ut.business.user.repository;

import com.ut.business.user.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, String>,JpaSpecificationExecutor {

    User findByLoginNameAndPassWord(String loginName, String passWord);

    List<User> findByIdNotIn(List<String> ids);

    User findByLoginName(String loginName);
}
