package com.example.proxy.rest.handler;

import com.example.proxy.model.ServiceDefinition;
import com.example.proxy.rest.dto.ServiceDefinitionDto;
import com.example.proxy.rest.dto.common.PaginationResponse;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.mapper.ServiceDefinitionMapper;
import com.example.proxy.service.ServiceDefinitionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ServiceDefinitionHandler {

    private ServiceDefinitionMapper serviceDefinitionMapper;
    private ServiceDefinitionService serviceDefinitionService;


//    public ResponseEntity<?> create(ServiceDefinitionDto serviceDefinitionDto) {
//        try {
//            ServiceDefinition serviceDefinition = serviceDefinitionMapper.toServiceDefinition(serviceDefinitionDto);
//            serviceDefinitionService.save(serviceDefinition);
//            return ResponseEntity.status(HttpStatus.CREATED).body(serviceDefinition);
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(
//                    new Response(psqlException.getError(ex)));
//        }
//    }
//
//    public ResponseEntity<?> update(Long id, ServiceDefinitionDto serviceDefinitionDto) throws ResourceNotFound {
//        ServiceDefinition serviceDefinition = serviceDefinitionMapper.toServiceDefinition(serviceDefinitionDto);
//        ServiceDefinition serviceDefinitionById = serviceDefinitionService.findById(id)
//                .orElseThrow(() -> new ResourceNotFound("Vote service definition of id " + id + " Not Found"));
//        serviceDefinitionById.setName(serviceDefinition.getName() != null ? serviceDefinition.getName() : serviceDefinitionById.getName());
//        serviceDefinitionService.save(serviceDefinitionById);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceDefinitionById);
//    }

    public ResponseEntity<?> getById(Long id) throws ResourceNotFound {
        ServiceDefinition serviceDefinition = serviceDefinitionService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The service definition of id " + id + " Not Found"));
        ServiceDefinitionDto serviceDefinitionDto = serviceDefinitionMapper.toServiceDefinitionDto(serviceDefinition);
        return ResponseEntity.ok(serviceDefinitionDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<ServiceDefinition> serviceDefinitions = serviceDefinitionService.getAll(pageNo, pageSize);
        List<ServiceDefinition> serviceDefinitionList = serviceDefinitions.getContent();
        List<ServiceDefinitionDto> content= serviceDefinitionList.stream()
                .map(serviceDefinition ->  serviceDefinitionMapper.toServiceDefinitionDto(serviceDefinition)).collect(Collectors.toList());
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(content);
        paginationResponse.setPageNo(serviceDefinitions.getNumber()+1);
        paginationResponse.setPageSize(serviceDefinitions.getSize());
        paginationResponse.setTotalElements(serviceDefinitions.getTotalElements());
        paginationResponse.setTotalPages(serviceDefinitions.getTotalPages());

        return ResponseEntity.ok(paginationResponse);
    }

//    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
//        ServiceDefinition serviceDefinitionId = serviceDefinitionService.findById(id)
//                .orElseThrow(() -> new ResourceNotFound("service definition of id " + id + " Not Found"));
//        serviceDefinitionService.deleteById(id);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
//    }

}
