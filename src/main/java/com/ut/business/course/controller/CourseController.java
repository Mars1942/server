package com.ut.business.course.controller;

import com.ut.business.common.BackResult;
import com.ut.business.course.domain.Course;
import com.ut.business.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public BackResult findById(@RequestParam("id") String id) throws Exception{
        Course course = courseService.findById(id);
        BackResult<Course> br = new BackResult<>(course);
        return br;
    }
}
