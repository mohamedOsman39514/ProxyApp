package com.example.proxy.rest.controller;

import com.example.proxy.model.User;
import com.example.proxy.rest.handler.AuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthHandler authHandler;


    @PostMapping("/login")
    public ResponseEntity<?> createToken(@Valid @RequestBody User user) throws Exception {
        return authHandler.createToken(user);
    }


    @RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
        return authHandler.refreshtoken(request);

    }

}