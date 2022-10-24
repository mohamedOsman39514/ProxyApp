package com.example.proxy.model;

import com.example.proxy.model.common.JPA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "service_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest extends JPA {

    @Column
    private Integer amount;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Request request;

}
