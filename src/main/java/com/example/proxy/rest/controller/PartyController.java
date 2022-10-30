package com.example.proxy.rest.controller;

import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.handler.PartyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/party")
@Tag(name = "Party", description = "Rest Api For Party")
public class PartyController {

    private PartyHandler partyHandler;

    @GetMapping
    @Operation(summary = "get all parties")
    public ResponseEntity<List<?>> getAll() {
        return partyHandler.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get party By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) throws ResourceNotFound {
        return partyHandler.getById(id);
    }
}
