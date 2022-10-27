package com.example.proxy.rest.mapper;

import com.example.proxy.model.Status;
import com.example.proxy.rest.dto.StatusDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    List<StatusDto> toStatusDtos(List<Status> statuses);

    Status toStatus(StatusDto statusDto);

    StatusDto toStatusDto(Status status);
}
