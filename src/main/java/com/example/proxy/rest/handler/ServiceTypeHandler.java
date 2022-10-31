package com.example.proxy.rest.handler;

import com.example.proxy.model.ServiceType;
import com.example.proxy.rest.dto.ServiceTypeDto;
import com.example.proxy.rest.dto.common.PaginationResponse;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.mapper.ServiceTypeMapper;
import com.example.proxy.service.ServiceTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ServiceTypeHandler {

    private ServiceTypeMapper serviceTypeMapper;
    private ServiceTypeService serviceTypeService;

//    private PSQLException psqlException;

//    public ResponseEntity<?> create(ServiceTypeDto serviceTypeDto) {
//        try {
//            ServiceType serviceType = serviceTypeMapper.toServiceType(serviceTypeDto);
//            serviceTypeService.save(serviceType);
//            ServiceTypeDto serviceTypeDto1 = serviceTypeMapper.toServiceTypeDto(serviceType);
//            return ResponseEntity.status(HttpStatus.CREATED).body(serviceTypeDto1);
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(
//                    new Response(psqlException.getError(ex)));
//        }
//    }
//
//    public ResponseEntity<?> update(Long id, ServiceTypeDto serviceTypeDto) throws ResourceNotFound {
//        ServiceType serviceType = serviceTypeMapper.toServiceType(serviceTypeDto);
//        ServiceType serviceTypeById = serviceTypeService.findById(id)
//                .orElseThrow(() -> new ResourceNotFound("service type of id " + id + " Not Found"));
//        serviceTypeById.setName(serviceType.getName() != null ? serviceType.getName() : serviceTypeById.getName());
//        serviceTypeService.save(serviceTypeById);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceTypeById);
//    }

    public ResponseEntity<?> getById(Long id)
            throws ResourceNotFound {
        ServiceType serviceType = serviceTypeService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The service type of id " + id + " Not Found"));
        ServiceTypeDto serviceTypeDto = serviceTypeMapper.toServiceTypeDto(serviceType);
        return ResponseEntity.ok(serviceTypeDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<ServiceType> serviceTypes = serviceTypeService.getAll(pageNo, pageSize);
        List<ServiceType> serviceTypeList = serviceTypes.getContent();
        List<ServiceTypeDto> content= serviceTypeList.stream().map(serviceType ->  serviceTypeMapper.toServiceTypeDto(serviceType)).collect(Collectors.toList());
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(content);
        paginationResponse.setPageNo(serviceTypes.getNumber()+1);
        paginationResponse.setPageSize(serviceTypes.getSize());
        paginationResponse.setTotalElements(serviceTypes.getTotalElements());
        paginationResponse.setTotalPages(serviceTypes.getTotalPages());

        return ResponseEntity.ok(paginationResponse);
    }

//    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
//        ServiceType serviceType = serviceTypeService.findById(id)
//                .orElseThrow(() -> new ResourceNotFound("service type of id " + id + " Not Found"));
//        serviceTypeService.deleteById(id);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
//    }

}
