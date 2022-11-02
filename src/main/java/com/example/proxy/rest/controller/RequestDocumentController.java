package com.example.proxy.rest.controller;

import com.example.proxy.rest.handler.RequestDocumentHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/document")
@Tag(name = "Document", description = "Rest Api For Documents")
public class RequestDocumentController {

    private RequestDocumentHandler requestDocumentHandler;

    @PostMapping("/{id}/request")
    @Operation(summary = "upload document by the request id")
    public ResponseEntity<?> uploadDocument(@PathVariable Long id,
                                            @RequestParam("file") MultipartFile file,
                                            @RequestParam("description") String desc) throws IOException {
        return requestDocumentHandler.upload(id, file,desc);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update document")
    public ResponseEntity<?> updateDocument(@PathVariable Long id,
                                            @RequestParam("file") MultipartFile file) throws IOException {
        return requestDocumentHandler.update(id, file);
    }

    @GetMapping("/{id}")
    @Operation(summary = "find document by id")
    public ResponseEntity<?> getImageByName(@PathVariable Long id) throws IOException {
        return requestDocumentHandler.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete document By Id")
    public ResponseEntity<?> delete(@PathVariable Long id)   {
        return requestDocumentHandler.delete(id);
    }

    @GetMapping
    @Operation(summary = "search for files by description")
    public ResponseEntity<List<?>> getAll(@RequestParam("description") String description) {
        return requestDocumentHandler.getByDescription(description);
    }

}
