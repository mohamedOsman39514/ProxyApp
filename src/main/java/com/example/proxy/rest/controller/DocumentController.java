package com.example.proxy.rest.controller;

import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.handler.DocumentHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/document")
@Tag(name = "Document", description = "Rest Api For Documents")
public class DocumentController {

    @Autowired
    private DocumentHandler documentHandler;

    @PostMapping("/{id}")
    @Operation(summary = "upload document by the request id")
    public ResponseEntity<?> uploadDocument(@PathVariable Long id, @RequestParam("image") MultipartFile file) throws IOException, ResourceNotFound {
        return documentHandler.upload(id, file);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update document")
    public ResponseEntity<?> updateDocument(@PathVariable Long id,@RequestParam("image") MultipartFile file) throws IOException, ResourceNotFound {
        return documentHandler.update(id, file);
    }

    @GetMapping("/{name}")
    @Operation(summary = "find document by name")
    public ResponseEntity<?> getImageByName(@PathVariable("name") String name) throws IOException {
        return documentHandler.getByName(name);
    }

}
