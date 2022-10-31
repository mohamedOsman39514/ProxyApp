package com.example.proxy.rest.controller;

import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.handler.GovernorateHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/governorate")
@Tag(name = "Governorate", description = "Rest Api For Governorate")
public class GovernorateController {

    private GovernorateHandler governorateHandler;

    @GetMapping
    @Operation(summary = "get all governorate")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo, @RequestParam(value = "size") Integer pageSize){
        if (pageNo <= 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("no page number: 0"));
        return governorateHandler.getAll(pageNo -1, pageSize);
    }
    @GetMapping("/{id}")
    @Operation(summary = "get governorate By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) throws ResourceNotFound {
        return governorateHandler.getById(id);
    }
}
