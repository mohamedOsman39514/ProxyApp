package com.example.proxy.rest.controller;

import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.handler.StatusHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/status")
@Tag(name = "Status", description = "Rest Api For Status")
public class StatusController {

    private StatusHandler statusHandler;


    @GetMapping
    @Operation(summary = "get all statuses")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo, @RequestParam(value = "size") Integer pageSize){
        if (pageNo <= 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("no page number: 0"));
        return statusHandler.getAll(pageNo -1, pageSize);
    }
    @GetMapping("/{id}")
    @Operation(summary = "get status By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id)
            throws ResourceNotFound {
        return statusHandler.getById(id);
    }

//    @PostMapping
//    @Operation(summary = "create new status")
//    public ResponseEntity<?> create(@Valid @RequestBody StatusDto statusDto) {
//        return statusHandler.create(statusDto);
//    }

//    @PutMapping("/{id}")
//    @Operation(summary = "update details for status")
//    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody StatusDto statusDto) throws ResourceNotFound {
//        return statusHandler.update(id, statusDto);
//    }

//    @DeleteMapping("/{id}")
//    @Operation(summary = "delete status By Id")
//    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
//        return statusHandler.delete(id);
//    }
}
