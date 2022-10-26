package com.example.proxy.rest.controller;

import com.example.proxy.rest.dto.RequestDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.handler.RequestHandler;
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
@RequestMapping("/request")
@Tag(name = "Request", description = "Rest Api For Request")
public class RequestController {

    @Autowired
    private RequestHandler requestHandler;


    @GetMapping
    @Operation(summary = "get all requests")
    public ResponseEntity<List<?>> getAll() {
        return requestHandler.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get request By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id)
            throws ResourceNotFound {
        return requestHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "create new request")
    public ResponseEntity<?> create(@Valid @RequestBody RequestDto requestDto) {
        return requestHandler.create(requestDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update status for request")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody RequestDto requestDto) throws ResourceNotFound {
        return requestHandler.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete request By Id")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
        return requestHandler.delete(id);
    }
}
