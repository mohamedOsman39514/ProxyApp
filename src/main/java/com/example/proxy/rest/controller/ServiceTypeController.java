package com.example.proxy.rest.controller;

import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.handler.ServiceTypeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/service_type")
@Tag(name = "Service Type", description = "Rest Api For Service Type")
public class ServiceTypeController {

    private ServiceTypeHandler serviceTypeHandler;


    @GetMapping
    @Operation(summary = "get all service type")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo, @RequestParam(value = "size") Integer pageSize){
        if (pageNo <= 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("no page number: 0"));
        return serviceTypeHandler.getAll(pageNo -1, pageSize);
    }
    @GetMapping("/{id}")
    @Operation(summary = "get service type By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id)
            throws ResourceNotFound {
        return serviceTypeHandler.getById(id);
    }

//    @PostMapping
//    @Operation(summary = "create new service type")
//    public ResponseEntity<?> create(@Valid @RequestBody ServiceTypeDto serviceTypeDto) {
//        return serviceTypeHandler.create(serviceTypeDto);
//    }

//    @PutMapping("/{id}")
//    @Operation(summary = "update details for service type")
//    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ServiceTypeDto serviceTypeDto) throws ResourceNotFound {
//        return serviceTypeHandler.update(id, serviceTypeDto);
//    }

//    @DeleteMapping("/{id}")
//    @Operation(summary = "delete service type By Id")
//    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
//        return serviceTypeHandler.delete(id);
//    }
}
