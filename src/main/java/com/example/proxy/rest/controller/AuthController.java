package com.example.proxy.rest.controller;

import com.example.proxy.model.User;
import com.example.proxy.rest.handler.AuthHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@AllArgsConstructor
@RestController
public class AuthController {

    private AuthHandler authHandler;


    @PostMapping("/login")
    public ResponseEntity<?> createToken(@Valid @RequestBody User user) throws Exception {
        return authHandler.createToken(user);
    }

    @GetMapping(value = "/refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception {
        return authHandler.refreshtoken(request);

    }

}