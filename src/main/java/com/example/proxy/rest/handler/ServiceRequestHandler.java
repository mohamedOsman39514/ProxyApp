package com.example.proxy.rest.handler;

import com.example.proxy.model.ServiceRequest;
import com.example.proxy.rest.dto.ServiceRequestDto;
import com.example.proxy.rest.exception.PSQLException;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.mapper.ServiceRequestMapper;
import com.example.proxy.service.ServiceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ServiceRequestHandler {

    @Autowired
    private ServiceRequestMapper serviceRequestMapper;

    @Autowired
    private ServiceRequestService serviceRequestService;

    @Autowired
    private PSQLException psqlException;

    public ResponseEntity<?> create(ServiceRequestDto serviceRequestDto) {
        try {
            ServiceRequest serviceRequest = serviceRequestMapper.toServiceRequest(serviceRequestDto);
            serviceRequestService.save(serviceRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceRequest);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new Response(psqlException.getError(ex)));
        }
    }

    public ResponseEntity<?> update(Long id, ServiceRequestDto serviceRequestDto) throws ResourceNotFound {
        ServiceRequest serviceRequest = serviceRequestMapper.toServiceRequest(serviceRequestDto);
        ServiceRequest serviceRequestById = serviceRequestService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("service request of id " + id + " Not Found"));
        serviceRequestById.setAmount(serviceRequest.getAmount() != null ? serviceRequest.getAmount() : serviceRequestById.getAmount());
        serviceRequestById.setStartDate(serviceRequest.getStartDate() != null ? serviceRequest.getStartDate() : serviceRequestById.getStartDate());
        serviceRequestById.setEndDate(serviceRequest.getEndDate() != null ? serviceRequest.getEndDate() : serviceRequestById.getEndDate());
        serviceRequestService.save(serviceRequestById);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceRequestById);
    }

    public ResponseEntity<?> getById(Long id)
            throws ResourceNotFound {
        ServiceRequest serviceRequest = serviceRequestService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The service request of id " + id + " Not Found"));
        ServiceRequestDto serviceRequestDto = serviceRequestMapper.toServiceRequestDto(serviceRequest);
        return ResponseEntity.ok(serviceRequestDto);
    }

    public ResponseEntity<List<?>> getAll() {
        List<ServiceRequestDto> requestDtoList = serviceRequestMapper.toServiceRequestDtos(serviceRequestService.findAll());
        return ResponseEntity.ok(requestDtoList);
    }

    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
        ServiceRequest serviceRequest = serviceRequestService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("service request of id " + id + " Not Found"));
        serviceRequestService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }
}
