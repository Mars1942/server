package com.ut.business.course.repository;

import com.ut.business.course.domain.Course;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, String>,JpaSpecificationExecutor {
}
