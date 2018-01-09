package com.ut.business.course.controller;

import com.ut.business.course.domain.Course;

import java.util.List;

public class CourseVo extends Course {

    private String userId;

    private Integer isTeacher;//老师:1,学生0

    private List<String> ids;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Integer isTeacher) {
        this.isTeacher = isTeacher;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
