package com.ut.business.course.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.internal.Nullable;
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

    private String memo;// 备注

    private int time;//时长

    private Integer count=0;// 总人数

    private Integer hasCount=0;//已选课人数

    private String teacherId;

    private String teacherName;

    private List<UserToCourse> uTocList;

    private List<String> userIds;

    private String userId;

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

    public void setName(String name) {
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    //@Transient 注释掉不想关联数据库的属性

    @JsonIgnore
    @OneToMany(mappedBy="course",cascade = CascadeType.REMOVE)
    public List<UserToCourse> getuTocList() {
        return uTocList;
    }

    public void setuTocList(List<UserToCourse> uTocList) {
        this.uTocList = uTocList;
    }

    @Transient//注释掉不想关联数据库的属性
    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @Transient
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
