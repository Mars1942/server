package com.ut.business.user.controller;

import com.ut.business.user.domain.User;

import java.util.List;

/**
 * 用户查询对象
 */
public class UserVo extends User {

//    private String name;
//
//    private String loginName;
//
//    private String passWord;
//
//    private Integer age;
//
//    private Integer sex;    // 0：男；1：女
//
//    private Integer type = 0;

    private String roleCode; //角色Code

    private int isSel;  //是否选择（0：未选择该课程1:已选择该课程）

    private String courseId;

    private List<String> ids; //user的Id集合

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public int getIsSel() {
        return isSel;
    }

    public void setIsSel(int isSel) {
        this.isSel = isSel;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
