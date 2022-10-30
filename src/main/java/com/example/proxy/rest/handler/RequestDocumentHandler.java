package com.example.proxy.rest.handler;

import com.example.proxy.model.RequestDocument;
import com.example.proxy.model.Request;
import com.example.proxy.rest.dto.RequestDocumentDto;
import com.example.proxy.rest.dto.RequestDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.mapper.RequestDocumentMapper;
import com.example.proxy.rest.mapper.RequestMapper;
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


    public ResponseEntity<?> upload(Long id, MultipartFile file, String decs) throws IOException, ResourceNotFound {
        Request request = requestService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The Request of id " + id + " Not Found"));
        RequestDto requestDto = requestMapper.toRequestDto(request);
        RequestDocumentDto requestDocumentDto = new RequestDocumentDto();
        requestDocumentDto.setDescription(decs);
        requestDocumentDto.setName(file.getOriginalFilename());
        requestDocumentDto.setType(file.getContentType());
        requestDocumentDto.setFile(DocumentUtil.compressFile(file.getBytes()));
        requestDocumentDto.setRequest(requestDto);
        RequestDocument document = documentMapper.toDocument(requestDocumentDto);
        requestDocumentService.save(document);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Document uploaded successfully: " + file.getOriginalFilename()));
    }

    public ResponseEntity<?> update(Long id, MultipartFile file) throws IOException, ResourceNotFound {
        RequestDocument document = requestDocumentService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("document of id " + id + " Not Found"));
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
        List<RequestDocumentDto> requestDocuments = documentMapper.toDocumentDtos(requestDocumentService.searchDocumentByDescription(description));
        return ResponseEntity.ok(requestDocuments);
    }

    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
        RequestDocument requestDocument = requestDocumentService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Document of id " + id + " Not Found"));
        requestDocumentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }

}
