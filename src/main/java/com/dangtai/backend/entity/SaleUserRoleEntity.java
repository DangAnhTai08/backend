package com.dangtai.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "sale_user_role")
public class SaleUserRoleEntity implements Serializable {

    @Id
    @Column(name = "user_role_id")
    private long userRoleId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "role_id")
    private long roleId;
}
