package com.example.proxy.rest.controller;

import com.example.proxy.model.User;
import com.example.proxy.rest.handler.AuthHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@AllArgsConstructor
@RestController
@Tag(name = "Authentication", description = "Rest Api For Authentication ")
public class AuthController {

    private AuthHandler authHandler;


    @PostMapping("/login")
    @Operation(summary = "user login")
    public ResponseEntity<?> createToken(@Valid @RequestBody User user) throws Exception {
        return authHandler.createToken(user);
    }

    @GetMapping(value = "/refresh_token")
    @Operation(summary = "refresh token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception {
        return authHandler.refreshtoken(request);

    }

}