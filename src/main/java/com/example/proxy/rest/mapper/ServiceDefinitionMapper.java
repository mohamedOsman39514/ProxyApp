package com.example.proxy.rest.mapper;

import com.example.proxy.model.ServiceDefinition;
import com.example.proxy.rest.dto.ServiceDefinitionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceDefinitionMapper {

    List<ServiceDefinitionDto> toServiceDefinitionDtos(List<ServiceDefinition> serviceDefinitions);

    ServiceDefinition toServiceDefinition(ServiceDefinitionDto serviceDefinitionDto);

    ServiceDefinitionDto toServiceDefinitionDto(ServiceDefinition serviceDefinition);

}
