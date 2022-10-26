package com.example.proxy.rest.controller;

import com.example.proxy.rest.dto.ServiceRequestDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.handler.ServiceRequestHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/serviceRequest")
@Tag(name = "Service Request", description = "Rest Api For Service Request")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestHandler serviceRequestHandler;


    @GetMapping
    @Operation(summary = "get all service request")
    public ResponseEntity<List<?>> getAll() {
        return serviceRequestHandler.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get service request By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id)
            throws ResourceNotFound {
        return serviceRequestHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "create new service request")
    public ResponseEntity<?> create(@Valid @RequestBody ServiceRequestDto serviceRequestDto) {
        return serviceRequestHandler.create(serviceRequestDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update details for service request")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ServiceRequestDto serviceRequestDto) throws ResourceNotFound {
        return serviceRequestHandler.update(id, serviceRequestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete service request By Id")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
        return serviceRequestHandler.delete(id);
    }
}
