package com.example.proxy.rest.handler;

import com.example.proxy.model.Request;
import com.example.proxy.rest.dto.RequestDto;
import com.example.proxy.rest.exception.SQLException;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.mapper.RequestMapper;
import com.example.proxy.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RequestHandler {

//    @Autowired
    private RequestMapper requestMapper;

//    @Autowired
    private RequestService requestService;

//    @Autowired
    private SQLException psqlException;


    public ResponseEntity<?> create(RequestDto requestDto) {
        try {
            Request request = requestMapper.toRequest(requestDto);
            requestService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new Response(psqlException.getError(ex)));
        }
    }

    public ResponseEntity<?> update(Long id, RequestDto requestDto) throws ResourceNotFound {
        Request request = requestMapper.toRequest(requestDto);
        Request requestById = requestService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("request of id " + id + " Not Found"));
        requestById.setStatus(request.getStatus() == null ? request.getStatus() : requestById.getStatus());
        requestService.save(requestById);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(requestById);
    }

    public ResponseEntity<RequestDto> getById(Long id) throws ResourceNotFound {
        Request request = requestService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The request of id " + id + " Not Found"));
        RequestDto requestDto = requestMapper.toRequestDto(request);
        return ResponseEntity.ok(requestDto);
    }

    public ResponseEntity<List<?>> getAll() {
        List<RequestDto> requestDtoList = requestMapper.toRequestDtos(requestService.findAll());
        return ResponseEntity.ok(requestDtoList);
    }

    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
        Request request = requestService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("request of id " + id + " Not Found"));
        requestService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }
}
