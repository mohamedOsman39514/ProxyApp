package com.example.proxy.rest.controller;

import com.example.proxy.rest.handler.PartyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/party")
@Tag(name = "Party", description = "Rest Api For Party")
public class PartyController {

    private PartyHandler partyHandler;

    @GetMapping
    @Operation(summary = "get all parties")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo,
                                    @RequestParam(value = "size") Integer pageSize) {
        return partyHandler.getAll(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get party By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return partyHandler.getById(id);
    }

}
