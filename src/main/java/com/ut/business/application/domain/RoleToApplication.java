package com.ut.business.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ut.business.role.domain.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class RoleToApplication {

    private String id;

    private Role role;

    private Application application;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne(cascade= CascadeType.REFRESH,optional=false)
    @JoinColumn(name = "application_id", insertable = false, updatable = false)
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
