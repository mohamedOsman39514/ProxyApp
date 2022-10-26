package com.example.proxy.rest.handler;

import com.example.proxy.model.ServiceDefinition;
import com.example.proxy.rest.dto.ServiceDefinitionDto;
import com.example.proxy.rest.exception.PSQLException;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.mapper.ServiceDefinitionMapper;
import com.example.proxy.service.ServiceDefinitionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ServiceDefinitionHandler {


    @Autowired
    private ServiceDefinitionMapper serviceDefinitionMapper;

    @Autowired
    private ServiceDefinitionService serviceDefinitionService;

    @Autowired
    private PSQLException psqlException;

    public ResponseEntity<?> create(ServiceDefinitionDto serviceDefinitionDto) {
        try {
            ServiceDefinition serviceDefinition = serviceDefinitionMapper.toServiceDefinition(serviceDefinitionDto);
            serviceDefinitionService.save(serviceDefinition);
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceDefinition);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new Response(psqlException.getError(ex)));
        }
    }

    public ResponseEntity<?> update(Long id, ServiceDefinitionDto serviceDefinitionDto) throws ResourceNotFound {
        ServiceDefinition serviceDefinition = serviceDefinitionMapper.toServiceDefinition(serviceDefinitionDto);
        ServiceDefinition serviceDefinitionById = serviceDefinitionService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Vote service definition of id " + id + " Not Found"));
        serviceDefinitionById.setName(serviceDefinition.getName() != null ? serviceDefinition.getName() : serviceDefinitionById.getName());
        serviceDefinitionService.save(serviceDefinitionById);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceDefinitionById);
    }

    public ResponseEntity<?> getById(Long id)
            throws ResourceNotFound {
        ServiceDefinition serviceDefinition = serviceDefinitionService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The service definition of id " + id + " Not Found"));
        ServiceDefinitionDto serviceDefinitionDto = serviceDefinitionMapper.toServiceDefinitionDto(serviceDefinition);
        return ResponseEntity.ok(serviceDefinitionDto);
    }

    public ResponseEntity<List<?>> getAll() {
        List<ServiceDefinitionDto> serviceDefinitionDtoList = serviceDefinitionMapper.toServiceDefinitionDtos(serviceDefinitionService.findAll());
        return ResponseEntity.ok(serviceDefinitionDtoList);
    }

    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
        ServiceDefinition serviceDefinitionId = serviceDefinitionService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("service definition of id " + id + " Not Found"));
        serviceDefinitionService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }

}
