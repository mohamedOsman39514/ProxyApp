package com.example.proxy.entity;

import com.example.proxy.entity.common.JPAEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status extends JPAEntity {

    @Column
    private String name;
}
