package com.example.proxy.model;

import com.example.proxy.model.common.JPAModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends JPAModel {

    @Column
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "governorate_id")
    private Governorate governorate;

    @Column
    private String address;

    @Column(unique = true)
    private String nationalId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id")
    private List<Role> roles = new ArrayList<>();

}
