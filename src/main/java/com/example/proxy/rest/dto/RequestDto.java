package com.example.proxy.rest.dto;

import com.example.proxy.rest.dto.common.RestDto;
import lombok.Data;

import java.util.List;

@Data

public class RequestDto extends RestDto {

    private Boolean status;

    private ServiceTypeDto serviceType;

    private List<RequestDto> requests;

    private UserDto user;

}
