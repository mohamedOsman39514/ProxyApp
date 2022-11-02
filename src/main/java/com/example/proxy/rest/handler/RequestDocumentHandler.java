package com.example.proxy.rest.handler;

import com.example.proxy.entity.RequestDocument;
import com.example.proxy.entity.Request;
import com.example.proxy.rest.dto.RequestDocumentDto;
import com.example.proxy.rest.dto.RequestDto;
import com.example.proxy.rest.exception.ResourceNotFoundException;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.entitymapper.RequestDocumentMapper;
import com.example.proxy.rest.entitymapper.RequestMapper;
import com.example.proxy.service.RequestDocumentService;
import com.example.proxy.service.RequestService;
import com.example.proxy.utils.DocumentUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class RequestDocumentHandler {

    private RequestDocumentService requestDocumentService;
    private RequestDocumentMapper documentMapper;
    private RequestMapper requestMapper;
    private RequestService requestService;

    public ResponseEntity<?> upload(Long id, MultipartFile file, String decs) throws IOException {
        Request request = requestService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Request.class.getSimpleName(),id));
        RequestDto requestDto = requestMapper.toDto(request);
        RequestDocumentDto requestDocumentDto = new RequestDocumentDto();
        requestDocumentDto.setDescription(decs);
        requestDocumentDto.setName(file.getOriginalFilename());
        requestDocumentDto.setType(file.getContentType());
        requestDocumentDto.setFile(DocumentUtil.compressFile(file.getBytes()));
        requestDocumentDto.setRequest(requestDto);
        RequestDocument document = documentMapper.toEntity(requestDocumentDto);
        requestDocumentService.save(document);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Document uploaded successfully: " + file.getOriginalFilename()));
    }

    public ResponseEntity<?> update(Long id, MultipartFile file) throws IOException {
        RequestDocument document = requestDocumentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RequestDocument.class.getSimpleName(),id));
//        document.setDescription(description);
        document.setName(file.getOriginalFilename());
        document.setType(file.getContentType());
        document.setFile(DocumentUtil.compressFile(file.getBytes()));
        requestDocumentService.save(document);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Document updated successfully: " + file.getOriginalFilename()));
    }

    public ResponseEntity<?> getById(Long id) throws IOException {
        RequestDocument document = requestDocumentService.findById(id).get();
        if (document == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document Not Found");
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(document.getType()))
                .body(DocumentUtil.decompressFile(document.getFile()));
    }

    public ResponseEntity<List<?>> getByDescription(String description) {
        List<RequestDocumentDto> requestDocuments = documentMapper.toDto(requestDocumentService.searchDocumentByDescription(description));
        return ResponseEntity.ok(requestDocuments);
    }

    public ResponseEntity<?> delete(Long id) {
        requestDocumentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RequestDocument.class.getSimpleName(),id));
        requestDocumentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
