package com.example.proxy.rest.handler;

import com.example.proxy.model.*;
import com.example.proxy.rest.dto.ServiceRequestDto;
import com.example.proxy.rest.dto.common.PaginationResponse;
import com.example.proxy.rest.exception.SQLException;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.mapper.ServiceRequestMapper;
import com.example.proxy.service.RequestService;
import com.example.proxy.service.ServiceRequestService;
import com.example.proxy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ServiceRequestHandler {

    private ServiceRequestMapper serviceRequestMapper;
    private ServiceRequestService serviceRequestService;
    private RequestService requestService;
    private UserService userService;


    public ResponseEntity<?> create(ServiceRequestDto serviceRequestDto) {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(email);
            ServiceRequest serviceRequest = serviceRequestMapper.toServiceRequest(serviceRequestDto);
            Request request = requestService.findById(serviceRequest.getRequest().getId()).get();
            serviceRequestService.save(serviceRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceRequest);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SQLException(ex));
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

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<ServiceRequest> requests = serviceRequestService.getAll(pageNo, pageSize);
        List<ServiceRequest> requestList = requests.getContent();
        List<ServiceRequestDto> content= requestList.stream().map(serviceRequest ->  serviceRequestMapper.toServiceRequestDto(serviceRequest)).collect(Collectors.toList());
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(content);
        paginationResponse.setPageNo(requests.getNumber()+1);
        paginationResponse.setPageSize(requests.getSize());
        paginationResponse.setTotalElements(requests.getTotalElements());
        paginationResponse.setTotalPages(requests.getTotalPages());

        return ResponseEntity.ok(paginationResponse);
    }

    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
        ServiceRequest serviceRequest = serviceRequestService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("service request of id " + id + " Not Found"));
        serviceRequestService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }
}
