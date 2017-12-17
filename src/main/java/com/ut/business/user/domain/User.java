package com.ut.business.user.domain;

import com.ut.business.common.BaseEntity;
import com.ut.business.course.domain.UserToCourse;
import com.ut.business.role.domain.Role;
import com.ut.business.role.domain.UserToRole;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 雇员:  先开发实体类===>自动生成数据表
 */
@Entity
public class User extends BaseEntity{

    private String id;

    private String name;

    private String loginName;

    private String passWord;

    private Integer age;

    private Integer sex;    // 0：男；1：女

    private Integer type = 0;      //用户类型 0：普通用户 1：admin管理员

    private List<UserToRole> uToRList;

    private List<UserToCourse> uTocList;

    private List<String> roleIds;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

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

    @Column(length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() { return sex; }

    public void setSex(Integer sex) { this.sex = sex; }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @OneToMany(mappedBy="user",cascade = CascadeType.REMOVE/*, fetch = FetchType.LAZY*/)
    public List<UserToRole> getuToRList() {
        return uToRList;
    }

    public void setuToRList(List<UserToRole> uToRList) {
        this.uToRList = uToRList;
    }

    @OneToMany(mappedBy="user",cascade = CascadeType.REMOVE)
    public List<UserToCourse> getuTocList() {
        return uTocList;
    }

    public void setuTocList(List<UserToCourse> uTocList) {
        this.uTocList = uTocList;
    }

    @Transient
    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

}
