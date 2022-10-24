package com.example.proxy.rest.mapper;

import com.example.proxy.model.ServiceType;
import com.example.proxy.rest.dto.ServiceTypeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceTypeMapper {

    List<ServiceTypeDto> toServiceTypeDtos(List<ServiceType> serviceTypes);

    ServiceType toServiceType(ServiceTypeDto serviceTypeDto);

    ServiceTypeDto toServiceTypeDto(ServiceType serviceType);

}
