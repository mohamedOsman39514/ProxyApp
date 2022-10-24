package com.example.proxy.rest.controller;

import com.example.proxy.model.ServiceDefinition;
import com.example.proxy.rest.dto.ServiceDefinitionDto;
import com.example.proxy.rest.exception.PSQLException;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.mapper.ServiceDefinitionMapper;
import com.example.proxy.service.ServiceDefinitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/serviceDefinition")
@Tag( name = "Service Definition",description ="Rest Api For Service Definition")
public class ServiceDefinitionController {


    @Autowired
    private ServiceDefinitionMapper serviceDefinitionMapper;

    @Autowired
    private ServiceDefinitionService serviceDefinitionService;

    @Autowired
    private PSQLException psqlException;

    @GetMapping
    public ResponseEntity<List<ServiceDefinitionDto>> getAll() {
        List<ServiceDefinitionDto> serviceDefinitionDtoList = serviceDefinitionMapper.toServiceDefinitionDtos(serviceDefinitionService.findAll());
        return ResponseEntity.ok(serviceDefinitionDtoList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get service definition By Id")
    public ResponseEntity<ServiceDefinitionDto> getById(@PathVariable(value = "id") Long id)
            throws ResourceNotFound {
        ServiceDefinition serviceDefinition = serviceDefinitionService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The service definition of id " + id + " Not Found"));
        ServiceDefinitionDto serviceDefinitionDto = serviceDefinitionMapper.toServiceDefinitionDto(serviceDefinition);
        return ResponseEntity.ok(serviceDefinitionDto);
    }

    @PostMapping
    @Operation(summary = "create new service definition")
    public ResponseEntity<?> create(@Valid @RequestBody ServiceDefinitionDto serviceDefinitionDto) {
        try {
            ServiceDefinition serviceDefinition = serviceDefinitionMapper.toServiceDefinition(serviceDefinitionDto);
            serviceDefinitionService.save(serviceDefinition);
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceDefinition);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new Response(psqlException.getError(ex)));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "update details for service definition")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ServiceDefinitionDto serviceDefinitionDto)
            throws ResourceNotFound {
        ServiceDefinition serviceDefinition = serviceDefinitionMapper.toServiceDefinition(serviceDefinitionDto);
        ServiceDefinition serviceDefinitionById = serviceDefinitionService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Vote service definition of id " + id + " Not Found"));

        serviceDefinitionById.setName(serviceDefinition.getName() != null ? serviceDefinition.getName() : serviceDefinitionById.getName());
        serviceDefinitionService.save(serviceDefinitionById);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceDefinitionById);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete service definition By Id")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
        ServiceDefinition serviceDefinitionId = serviceDefinitionService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("service definition of id " + id + " Not Found"));
        serviceDefinitionService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }
}
