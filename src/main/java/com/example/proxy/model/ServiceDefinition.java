package com.example.proxy.model;

import com.example.proxy.model.common.JPA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "service_definition")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDefinition extends JPA {

    @Column
    private String name;

}
