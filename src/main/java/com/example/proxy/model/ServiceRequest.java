package com.example.proxy.model;

import com.example.proxy.model.common.JPAModel;
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
public class ServiceRequest extends JPAModel {

    @Column
    private Integer amount;

    @Column
    private Integer deservedAmount;

    @Column
    private Integer paidAmount;

    @Column
    private Integer remainingAmount;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request request;

}
