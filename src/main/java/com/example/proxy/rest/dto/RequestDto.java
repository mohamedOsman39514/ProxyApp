package com.example.proxy.rest.dto;

import com.example.proxy.rest.dto.common.RestDto;
import lombok.Data;

import java.time.LocalDate;


@Data
public class RequestDto extends RestDto {

    private Integer amount;

    private Integer deservedAmount;

    private Integer paidAmount;

    private Integer remainingAmount;

    private LocalDate startDate;

    private LocalDate endDate;

    private StatusDto status;

    private ServiceTypeDto serviceType;

    private RequestDto parentRequest;

    private UserDto requester;

}
