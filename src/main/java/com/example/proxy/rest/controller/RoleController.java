package com.example.proxy.rest.controller;

import com.example.proxy.rest.dto.RoleDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.handler.RoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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
    public List<RoleDto> getAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return roleHandler.getRoles(page, size);
    }

}
