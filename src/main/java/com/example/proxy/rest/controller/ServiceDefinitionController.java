package com.example.proxy.rest.controller;

import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.handler.ServiceDefinitionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/serviceDefinition")
@Tag(name = "Service Definition", description = "Rest Api For Service Definition")
public class ServiceDefinitionController {

    private ServiceDefinitionHandler serviceDefinitionHandler;


    @GetMapping
    @Operation(summary = "get all service definition")
    public ResponseEntity<List<?>> getAll() {
        return serviceDefinitionHandler.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get service definition By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) throws ResourceNotFound {
        return serviceDefinitionHandler.getById(id);
    }

//    @PostMapping
//    @Operation(summary = "create new service definition")
//    public ResponseEntity<?> create(@Valid @RequestBody ServiceDefinitionDto serviceDefinitionDto) {
//        return serviceDefinitionHandler.create(serviceDefinitionDto);
//    }

//    @PutMapping("/{id}")
//    @Operation(summary = "update details for service definition")
//    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ServiceDefinitionDto serviceDefinitionDto) throws ResourceNotFound {
//        return serviceDefinitionHandler.update(id, serviceDefinitionDto);
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "delete service definition By Id")
//    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
//        return serviceDefinitionHandler.delete(id);
//    }
}

