package com.example.proxy.entity;

import com.example.proxy.entity.common.JPAEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends JPAEntity {

    @Column
    private String name;
}
