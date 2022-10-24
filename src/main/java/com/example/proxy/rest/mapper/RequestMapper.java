package com.example.proxy.rest.mapper;

import com.example.proxy.model.Request;
import com.example.proxy.rest.dto.RequestDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    List<RequestDto> toRequestDtos(List<Request> requests);

    Request toRequest(RequestDto requestDto);

    RequestDto toRequestDto(Request request);
}
