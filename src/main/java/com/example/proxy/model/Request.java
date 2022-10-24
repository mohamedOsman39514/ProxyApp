package com.example.proxy.model;

import com.example.proxy.model.common.JPA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request extends JPA {

    @Column
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    private ServiceType serviceType;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
