package com.ut.business.course.service;

import com.ut.business.course.domain.Course;
import com.ut.business.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public String save(Course course) throws Exception{ return courseRepository.save(course).getId();}

    public Page<Course> findAll(int pageNumber, int pageSize) throws Exception{
        Sort sort = new Sort("courseName");
        PageRequest request = new PageRequest(pageNumber,pageSize,sort);
        return courseRepository.findAll(request);
    }

}
