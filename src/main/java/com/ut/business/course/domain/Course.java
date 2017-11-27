package com.ut.business.course.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ut.business.user.domain.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * 课程
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {

    private String id;

    private String courseName;

    private String memo;

    private String time;//时长

    private Integer count=0;

    private Integer hasCount=0;//已选课人数

    private User teacher;

    @Id
    @GeneratedValue(generator = "uuid",strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 32, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getHasCount() {
        return hasCount;
    }

    public void setHasCount(Integer hasCount) {
        this.hasCount = hasCount;
    }

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

}
