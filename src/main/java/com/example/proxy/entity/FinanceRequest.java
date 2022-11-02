//package com.example.proxy.entity;
//
//import com.example.proxy.entity.common.LookupEntity;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class FinanceRequest extends LookupEntity {
//
//    @Column
//    private Integer amount;
//
//    @Column
//    private Integer deservedAmount;
//
//    @Column
//    private Integer paidAmount;
//
//    @Column
//    private Integer remainingAmount;
//
//    @Column
//    private LocalDate startDate;
//
//    @Column
//    private LocalDate endDate;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "request_id")
//    private Request request;
//
//}
