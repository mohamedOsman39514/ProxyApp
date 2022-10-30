package com.example.proxy.model;

import com.example.proxy.model.common.JPA;
import com.example.proxy.model.common.JPAModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status extends JPA {

    @Column
    private String name;
}
