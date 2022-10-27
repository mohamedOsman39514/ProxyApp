package com.example.proxy.rest.handler;

import com.example.proxy.model.Document;
import com.example.proxy.model.Request;
import com.example.proxy.rest.dto.DocumentDto;
import com.example.proxy.rest.dto.RequestDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.mapper.DocumentMapper;
import com.example.proxy.rest.mapper.RequestMapper;
import com.example.proxy.service.DocumentService;
import com.example.proxy.service.RequestService;
import com.example.proxy.utils.DocumentUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@AllArgsConstructor
public class DocumentHandler {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private RequestService requestService;


    public ResponseEntity<?> upload(Long id, MultipartFile file) throws IOException, ResourceNotFound {
        Request request = requestService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The Request of id " + id + " Not Found"));
        RequestDto requestDto = requestMapper.toRequestDto(request);
        DocumentDto documentDto = new DocumentDto();
        documentDto.setName(file.getOriginalFilename());
        documentDto.setType(file.getContentType());
        documentDto.setImage(DocumentUtil.compressImage(file.getBytes()));
        documentDto.setRequest(requestDto);
        Document document = documentMapper.toDocument(documentDto);
        documentService.save(document);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Document uploaded successfully: " + file.getOriginalFilename()));
    }

    public ResponseEntity<?> update(Long id, MultipartFile file) throws IOException, ResourceNotFound {
        Document document = documentService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("document of id " + id + " Not Found"));
        document.setName(file.getOriginalFilename());
        document.setType(file.getContentType());
        document.setImage(DocumentUtil.compressImage(file.getBytes()));
        documentService.save(document);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Document updated successfully: " + file.getOriginalFilename()));
    }

    public ResponseEntity<?> getByName(String name) throws IOException {
        Document document = documentService.findDocumentByName(name);
        if (document == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document Not Found");
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(document.getType()))
                .body(DocumentUtil.decompressImage(document.getImage()));
    }

}
