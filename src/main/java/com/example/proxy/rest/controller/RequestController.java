package com.example.proxy.rest.controller;

import com.example.proxy.rest.dto.RequestDto;
import com.example.proxy.rest.handler.RequestHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/request")
@Tag(name = "Request", description = "Rest Api For Request")
public class RequestController {

    private RequestHandler requestHandler;

    @GetMapping
    @Operation(summary = "get all requests")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo,
                                    @RequestParam(value = "size") Integer pageSize) {
        return requestHandler.getAll(pageNo, pageSize);
    }

    @GetMapping("/status/{statusName}")
    @Operation(summary = "get all requests by status")
    public ResponseEntity<?> getAllByStatus(@PathVariable(value = "statusName") String statusName,
                                            @RequestParam(value = "page") Integer pageNo,
                                            @RequestParam(value = "size") Integer pageSize) {
        return requestHandler.getAllByStatus(statusName, pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get request By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return requestHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "create new request")
    public ResponseEntity<?> create(@Valid @RequestBody RequestDto requestDto) {
        return requestHandler.create(requestDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update status for request")
    public ResponseEntity<?> updateRequestStatus(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        return requestHandler.updateRequestStatus(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete request By Id")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return requestHandler.delete(id);
    }

}
