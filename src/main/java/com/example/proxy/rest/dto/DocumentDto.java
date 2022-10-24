package com.example.proxy.rest.dto;

import com.example.proxy.model.Request;
import com.example.proxy.rest.dto.common.RestDto;
import lombok.Data;

@Data
public class DocumentDto extends RestDto {

    private String name;

    private String type;

    private byte[] image;

    private Request request;

}
