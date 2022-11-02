package com.example.proxy.rest.entitymapper;

import com.example.proxy.entity.Request;
import com.example.proxy.rest.dto.RequestDto;
import com.example.proxy.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestMapper extends JPAEntityMapper<Request, RequestDto> {
}
