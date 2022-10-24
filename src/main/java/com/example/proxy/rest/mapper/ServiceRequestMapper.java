package com.example.proxy.rest.mapper;

import com.example.proxy.model.ServiceRequest;
import com.example.proxy.rest.dto.ServiceRequestDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceRequestMapper {

    List<ServiceRequestDto> toServiceRequestDtos(List<ServiceRequest> serviceRequests);

    ServiceRequest toServiceRequest(ServiceRequestDto serviceRequestDto);

    ServiceRequestDto toServiceRequestDto(ServiceRequest serviceRequest);
}
