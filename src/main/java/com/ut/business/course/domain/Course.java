package com.ut.business.course.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ut.business.user.domain.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {

    private String id;

    private String name;

    private String memo;

    private int time;//时长

    private Integer count=0;

    private Integer hasCount=0;//已选课人数

    private User teacher;

    private String teacherId;

    private String teacherName;

    private List<UserToCourse> uTocList;

//    private List<User> UserList;

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

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
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

    @JsonIgnore
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    @Transient
    public String getTeacherId() {
        return teacher.getId();
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Transient
    public String getTeacherName() {
        return teacher.getName();
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @JsonIgnore
    @OneToMany(mappedBy="course")
    public List<UserToCourse> getuTocList() {
        return uTocList;
    }

    public void setuTocList(List<UserToCourse> uTocList) {
        this.uTocList = uTocList;
    }

//    @Transient
//    public List<User> getUserList() {
//        List<User> list = new ArrayList<User>();
//        for (UserToCourse userToCourse:uTocList) {
//            User user = userToCourse.getUser();
//            user.setuToRList(null);
//            user.setuTocList(null);
//            list.add(user);
//        }
//        return list;
//    }
//
//    public void setUserList(List<User> userList) {
//        UserList = userList;
//    }
}
