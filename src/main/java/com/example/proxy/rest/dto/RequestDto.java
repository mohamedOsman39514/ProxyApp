package com.example.proxy.rest.dto;

import com.example.proxy.model.ServiceType;
import com.example.proxy.model.User;
import com.example.proxy.rest.dto.common.RestDto;
import lombok.Data;

@Data

public class RequestDto extends RestDto {

    private Boolean status;

    private ServiceType serviceType;

    private User user;

}
