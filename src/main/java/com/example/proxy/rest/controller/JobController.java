package com.example.proxy.rest.controller;

import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.handler.JobHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/job")
@Tag(name = "Job", description = "Rest Api For Job")
public class JobController {

    private JobHandler jobHandler;

    @GetMapping
    @Operation(summary = "get all Job")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo, @RequestParam(value = "size") Integer pageSize){
        if (pageNo <= 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("no page number: 0"));
        return jobHandler.getAll(pageNo -1, pageSize);
    }
    @GetMapping("/{id}")
    @Operation(summary = "get Job By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) throws ResourceNotFound {
        return jobHandler.getById(id);
    }
}
