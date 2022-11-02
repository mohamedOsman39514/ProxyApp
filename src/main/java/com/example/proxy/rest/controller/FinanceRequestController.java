//package com.example.proxy.rest.controller;
//
//import com.example.proxy.rest.dto.FinanceRequestDto;
//import com.example.proxy.rest.exception.ResourceNotFound;
//import com.example.proxy.rest.exception.Response;
//import com.example.proxy.rest.handler.FinanceRequestHandler;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@AllArgsConstructor
//@RestController
//@RequestMapping("/service_request")
//@Tag(name = "Service Request", description = "Rest Api For Service Request")
//public class FinanceRequestController {
//
//    private FinanceRequestHandler financeRequestHandler;
//
//
//    @GetMapping
//    @Operation(summary = "get all service request")
//    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo, @RequestParam(value = "size") Integer pageSize){
//        if (pageNo <= 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("no page number: 0"));
//        return financeRequestHandler.getAll(pageNo -1, pageSize);
//    }
//    @GetMapping("/{id}")
//    @Operation(summary = "get service request By Id")
//    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id)
//            throws ResourceNotFound {
//        return financeRequestHandler.getById(id);
//    }
//
//    @PostMapping
//    @Operation(summary = "create new service request")
//    public ResponseEntity<?> create(@Valid @RequestBody FinanceRequestDto financeRequestDto) {
//        return financeRequestHandler.create(financeRequestDto);
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "update details for service request")
//    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody FinanceRequestDto financeRequestDto) throws ResourceNotFound {
//        return financeRequestHandler.update(id, financeRequestDto);
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "delete service request By Id")
//    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
//        return financeRequestHandler.delete(id);
//    }
//}
