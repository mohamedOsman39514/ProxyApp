package com.example.proxy.rest.controller;

import com.example.proxy.rest.dto.RequestDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.handler.RequestHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/request")
@Tag(name = "Request", description = "Rest Api For Request")
public class RequestController {

    private RequestHandler requestHandler;


    @GetMapping
    @Operation(summary = "get all requests")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo, @RequestParam(value = "size") Integer pageSize){
        if (pageNo <= 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("no page number: 0"));
        return requestHandler.getAll(pageNo -1, pageSize);
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
