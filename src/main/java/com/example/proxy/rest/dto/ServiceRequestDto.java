package com.example.proxy.rest.dto;

import com.example.proxy.model.Request;
import com.example.proxy.rest.dto.common.RestDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ServiceRequestDto extends RestDto {

    private Integer amount;

    private LocalDate startDate;

    private LocalDate endDate;

    private Request request;

}
