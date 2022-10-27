package com.example.proxy.rest.handler;

import com.example.proxy.model.ServiceType;
import com.example.proxy.rest.dto.ServiceTypeDto;
import com.example.proxy.rest.exception.PSQLException;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.mapper.ServiceTypeMapper;
import com.example.proxy.service.ServiceTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ServiceTypeHandler {

    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private PSQLException psqlException;

    public ResponseEntity<?> create(ServiceTypeDto serviceTypeDto) {
        try {
            ServiceType serviceType = serviceTypeMapper.toServiceType(serviceTypeDto);
            serviceTypeService.save(serviceType);
            ServiceTypeDto serviceTypeDto1 = serviceTypeMapper.toServiceTypeDto(serviceType);
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceTypeDto1);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new Response(psqlException.getError(ex)));
        }
    }

    public ResponseEntity<?> update(Long id, ServiceTypeDto serviceTypeDto) throws ResourceNotFound {
        ServiceType serviceType = serviceTypeMapper.toServiceType(serviceTypeDto);
        ServiceType serviceTypeById = serviceTypeService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("service type of id " + id + " Not Found"));
        serviceTypeById.setName(serviceType.getName() != null ? serviceType.getName() : serviceTypeById.getName());
        serviceTypeService.save(serviceTypeById);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceTypeById);
    }

    public ResponseEntity<?> getById(Long id)
            throws ResourceNotFound {
        ServiceType serviceType = serviceTypeService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The service type of id " + id + " Not Found"));
        ServiceTypeDto serviceTypeDto = serviceTypeMapper.toServiceTypeDto(serviceType);
        return ResponseEntity.ok(serviceTypeDto);
    }

    public ResponseEntity<List<?>> getAll() {
        List<?> serviceTypeDtoList = serviceTypeMapper.toServiceTypeDtos(serviceTypeService.findAll());
        return ResponseEntity.ok(serviceTypeDtoList);
    }

    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
        ServiceType serviceType = serviceTypeService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("service type of id " + id + " Not Found"));
        serviceTypeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }

}
