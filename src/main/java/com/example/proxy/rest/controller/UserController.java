package com.example.proxy.rest.controller;

import com.example.proxy.rest.dto.UserDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.handler.UserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Rest Api For User")
public class UserController {

    @Autowired
    private UserHandler userHandler;


    @GetMapping
    @Operation(summary = "get all users")
    public ResponseEntity<List<?>> getAll() {
        return userHandler.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get user By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id)
            throws ResourceNotFound {
        return userHandler.getById(id);
    }

    @PostMapping("/register")
    @Operation(summary = "sign up")
    public ResponseEntity<?> create(@Valid @RequestBody UserDto userDto) {
        return userHandler.register(userDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update details for user")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserDto userDto) throws ResourceNotFound {
        return userHandler.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete user By Id")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
        return userHandler.delete(id);
    }

    @PostMapping("/forgetPassword")
    @Operation(summary = "user forget password")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody UserDto userDto, HttpServletRequest request )
            throws ResourceNotFound {
        return userHandler.forgetPassword(userDto,request);
    }

    @PutMapping ("/resetPassword/{resetToken}")
    @Operation(summary = "user reset password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody UserDto userDto, @PathVariable String resetToken){
        return userHandler.resetPassword(userDto, resetToken);
    }

    @PatchMapping("/updatePassword/{newPassword}")
    @Operation(summary = "user change password")
    public ResponseEntity<?> changeUserPassword(@Valid @RequestBody UserDto userDto,@PathVariable String newPassword) {
        return userHandler.changeUserPassword(userDto, newPassword);
    }

}
