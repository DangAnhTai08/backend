package com.dangtai.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@Table(name = "sale_user")
public class SaleUserEntity implements Serializable {

    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_created")
    private Timestamp userCreated;

    @OneToMany
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Set<SaleUserRoleEntity> userRoleSet;
}
