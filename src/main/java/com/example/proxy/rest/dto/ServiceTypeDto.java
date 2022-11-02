package com.example.proxy.rest.dto;

import com.example.proxy.rest.dto.common.RestDto;
import lombok.Data;

@Data
public class ServiceTypeDto extends RestDto {

    private String name;

    private ServiceDefinitionDto service;

}
