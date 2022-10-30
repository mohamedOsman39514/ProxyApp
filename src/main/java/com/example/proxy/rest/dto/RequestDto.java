package com.example.proxy.rest.dto;

import com.example.proxy.rest.dto.common.RestDto;
import lombok.Data;


@Data

public class RequestDto extends RestDto {

    private StatusDto status;

    private ServiceTypeDto serviceType;

    private RequestDto request;

    private UserDto user;

}
