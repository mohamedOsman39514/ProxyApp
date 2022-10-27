package com.example.proxy.rest.dto;

import com.example.proxy.rest.dto.common.RestDto;
import lombok.Data;

@Data
public class UserDto extends RestDto {

    private String name;

    private String email;

    private String password;

    private Integer phone;

    private String job;

    private String jobPlace;

    private String address;

    private String nationalId;

}
