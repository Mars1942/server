package com.ut.business.course.service;

import com.ut.business.course.domain.Course;
import com.ut.business.course.domain.UserToCourse;
import com.ut.business.course.repository.CourseRepository;
import com.ut.business.course.repository.UserToCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserToCourseRepository userToCourseRepository;


    @Transactional
    public String save(Course course) throws Exception{ return courseRepository.save(course).getId();}

    @Transactional
    public void del(String id) throws Exception {
        courseRepository.delete(id);
        List<UserToCourse> list = userToCourseRepository.findByCourseId(id);
        for (UserToCourse userToCourse : list) {
            userToCourseRepository.delete(userToCourse);
        }
    }

    public Course findById(String id) {
        return courseRepository.findOne(id);
    }

    public Page<Course> findAll(int pageNumber, int pageSize) throws Exception{
        Sort sort = new Sort("courseName");
        PageRequest request = new PageRequest(pageNumber,pageSize,sort);
        return courseRepository.findAll(request);
    }

}
