package com.example.proxy.model;

import com.example.proxy.model.common.JPA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document extends JPA {

    @Column
    private String name;

    @Column
    private String type;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;

    @ManyToOne(fetch = FetchType.EAGER)
    private Request request;
}
