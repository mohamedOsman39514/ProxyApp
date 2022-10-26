package com.example.proxy.rest.controller;

import com.example.proxy.rest.dto.ServiceTypeDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.handler.ServiceTypeHandler;
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
@RequestMapping("/serviceType")
@Tag(name = "Service Type", description = "Rest Api For Service Type")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeHandler serviceTypeHandler;


    @GetMapping
    @Operation(summary = "get all service type")
    public ResponseEntity<List<?>> getAll() {
        return serviceTypeHandler.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get service type By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id)
            throws ResourceNotFound {
        return serviceTypeHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "create new service type")
    public ResponseEntity<?> create(@Valid @RequestBody ServiceTypeDto serviceTypeDto) {
        return serviceTypeHandler.create(serviceTypeDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update details for service type")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ServiceTypeDto serviceTypeDto) throws ResourceNotFound {
        return serviceTypeHandler.update(id, serviceTypeDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete service type By Id")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
        return serviceTypeHandler.delete(id);
    }
}
