package com.example.proxy.model;

import com.example.proxy.model.common.JPAModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDocument extends JPAModel {

    @Column
    private String name;

    @Column
    private String type;

    @Column(name = "file", unique = false, nullable = false, length = 100000)
    private byte[] file;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Request request;
}
