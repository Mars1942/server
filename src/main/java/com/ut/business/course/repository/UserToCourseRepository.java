package com.ut.business.course.repository;

import com.ut.business.course.domain.UserToCourse;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface UserToCourseRepository extends PagingAndSortingRepository<UserToCourse, String> {

    public List<UserToCourse> findByCourseId(String id);
}
