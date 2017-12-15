package com.ut.business.course.service;

import com.ut.business.course.domain.Course;
import com.ut.business.course.domain.UserToCourse;
import com.ut.business.course.repository.CourseRepository;
import com.ut.business.course.repository.UserToCourseRepository;
import com.ut.business.user.domain.User;
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
    public String save(Course course, List<String> userIds) throws Exception{
        String id = courseRepository.save(course).getId();
        if (userIds != null) {
            for (String userId: userIds) {
                UserToCourse userToCourse = new UserToCourse();
                course.setId(id);
                userToCourse.setCourse(course);
                User user = new User();
                user.setId(userId);
                userToCourse.setUser(user);
                userToCourseRepository.save(userToCourse);
            }
        }

        return id;
    }

    @Transactional
    public String update(Course course, List<String> userIds) {
        String id = courseRepository.save(course).getId();
        List<UserToCourse> list = userToCourseRepository.findByCourseId(id);
        if (!list.isEmpty()){
            for (UserToCourse userToCourse:list) {
                userToCourseRepository.delete(userToCourse);
            }
        }
        if (userIds != null) {
            for (String userId:userIds) {
                UserToCourse userToCourse = new UserToCourse();
                course.setId(id);
                userToCourse.setCourse(course);
                User user = new User();
                user.setId(userId);
                userToCourse.setUser(user);
                userToCourseRepository.save(userToCourse);
            }
        }
        return id;
    }

    @Transactional
    public void del(String id) throws Exception {
        courseRepository.delete(id);
//        List<UserToCourse> list = userToCourseRepository.findByCourseId(id);
//        for (UserToCourse userToCourse : list) {
//            userToCourseRepository.delete(userToCourse);
//        }
    }

    public Course findById(String id) {
        return courseRepository.findOne(id);
    }

    public Page<Course> findAll(int pageNumber, int pageSize) throws Exception{
        Sort sort = new Sort("name");
        PageRequest request = new PageRequest(pageNumber,pageSize,sort);
        return courseRepository.findAll(request);
    }

}
