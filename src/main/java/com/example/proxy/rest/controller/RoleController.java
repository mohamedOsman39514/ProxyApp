package com.example.proxy.rest.controller;

import com.example.proxy.rest.dto.RoleDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.handler.RoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/role")
@Tag(name = "Role", description = "Rest Api For Roles")
public class RoleController {

    private RoleHandler roleHandler;


//    @GetMapping
//    @Operation(summary = "get all roles")
//    public ResponseEntity<List<?>> getAll() {
//        return roleHandler.getAll();
//    }

    @GetMapping("/{id}")
    @Operation(summary = "get role By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) throws ResourceNotFound {
        return roleHandler.getById(id);
    }

    @GetMapping
//    @ResponseBody
    @Operation(summary = "get all roles")
    public ResponseEntity<?> getAll(@RequestParam(value = "page") Integer pageNo, @RequestParam(value = "size") Integer pageSize){
        if (pageNo <= 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("no page number: 0"));
        return roleHandler.getAll(pageNo -1, pageSize);
    }

}
