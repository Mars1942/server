package com.ut.business.role.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy="role",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public List<UserToRole> getuToRList() {
        return uToRList;
    }

    public void setuToRList(List<UserToRole> uToRList) {
        this.uToRList = uToRList;
    }

}
