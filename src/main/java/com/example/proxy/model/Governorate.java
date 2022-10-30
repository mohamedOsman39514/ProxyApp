package com.example.proxy.model;

import com.example.proxy.model.common.JPA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "governorate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Governorate extends JPA {

    @Column
    private String name;
}
