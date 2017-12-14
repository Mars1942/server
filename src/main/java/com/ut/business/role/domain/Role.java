package com.ut.business.role.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ut.business.application.domain.RoleToApplication;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 角色
 */
@Entity
public class Role {

    private String id;

    private String name;

    private String sort;

    private List<UserToRole> uToRList;

    private List<RoleToApplication> rToAList;

    private List<String> applicationIds;

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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @OneToMany(mappedBy="role",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    public List<UserToRole> getuToRList() {
        return uToRList;
    }

    public void setuToRList(List<UserToRole> uToRList) {
        this.uToRList = uToRList;
    }

    @OneToMany(mappedBy="role",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public List<RoleToApplication> getrToAList() {
        return rToAList;
    }

    public void setrToAList(List<RoleToApplication> rToAList) {
        this.rToAList = rToAList;
    }

    @Transient
    public List<String> getApplicationIds() {
        return applicationIds;
    }

    public void setApplicationIds(List<String> applicationIds) {
        this.applicationIds = applicationIds;
    }
}
