package com.ut.business.application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 应用
 */
@Entity
public class Application {

    private String id;

//    private Application parent;

    private String code;

    private String name;

    private String url;

    private int sort;

//    private List<Application> child;

    private List<RoleToApplication> rToAList;

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

//    @ManyToOne(optional = false, fetch = FetchType.EAGER)
//    @JoinColumn(name = "application_id")
//    public Application getParent() {
//        return parent;
//    }
//
//    public void setParent(Application parent) {
//        this.parent = parent;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    @OneToMany(mappedBy="application",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
//    public List<Application> getChild() {
//        return child;
//    }
//
//    public void setChild(List<Application> child) {
//        this.child = child;
//    }

    @OneToMany(mappedBy="application",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public List<RoleToApplication> getrToAList() {
        return rToAList;
    }

    public void setrToAList(List<RoleToApplication> rToAList) {
        this.rToAList = rToAList;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
