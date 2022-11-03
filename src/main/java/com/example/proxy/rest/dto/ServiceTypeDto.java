package com.example.proxy.rest.dto;

import com.example.proxy.rest.dto.common.LookupDto;
import com.example.proxy.rest.dto.common.RestDto;
import lombok.Data;

@Data
public class ServiceTypeDto extends LookupDto {

    private ServiceDefinitionDto service;

}
