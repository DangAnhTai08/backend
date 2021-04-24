package com.dangtai.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Table(name = "sale_admin")
public class SaleAdminEntity implements Serializable {

    @Id
    @Column(name = "admin_id")
    private long adminId;

    @Basic
    @Column(name = "admin_username")
    private String adminUsername;

    @Basic
    @Column(name = "admin_password")
    private String adminPassword;

    @Basic
    @Column(name = "admin_name")
    private String adminName;

    @Basic
    @Column(name = "admin_status")
    private Integer adminStatus;
}
