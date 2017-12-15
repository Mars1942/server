package com.ut.business.course.controller;

import com.ut.business.common.BackResult;
import com.ut.business.course.domain.Course;
import com.ut.business.course.service.CourseService;
import com.ut.business.pagingandsorting.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BackResult query(@RequestParam("pageNumber") int pageNumber) throws Exception{
        Page<Course> page = courseService.findAll(pageNumber, Constant.PAGE_SIZE);
        BackResult<Page<Course>> br = new BackResult<>(page);
        return br;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BackResult findById(@PathVariable String id) throws Exception{
        Course course = courseService.findById(id);
        BackResult<Course> br = new BackResult<>(course);
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BackResult del(@PathVariable String id) throws Exception{
        courseService.del(id);
        BackResult<String> br = new BackResult<>("");
        br.setMsg("删除成功");
        return br;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public BackResult add(@RequestBody Course course) throws Exception{
        BackResult<String> br = new BackResult<>(courseService.save(course, course.getUserIds()));
        br.setMsg("添加成功");
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BackResult update(@PathVariable String id, @RequestBody Course course) throws Exception{
        course.setId(id);
        BackResult<String> br = new BackResult<>(courseService.update(course,course.getUserIds()));
        br.setMsg("修改成功");
        return br;
    }

}
