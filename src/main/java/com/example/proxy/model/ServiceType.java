package com.example.proxy.model;

import com.example.proxy.model.common.JPA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "service_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceType extends JPA {

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceDefinition service;

}
