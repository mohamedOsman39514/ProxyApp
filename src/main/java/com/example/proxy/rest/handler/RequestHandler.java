package com.example.proxy.rest.handler;

import com.example.proxy.model.Request;
import com.example.proxy.rest.dto.RequestDto;
import com.example.proxy.rest.dto.common.PaginationResponse;
import com.example.proxy.rest.exception.SQLException;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.mapper.RequestMapper;
import com.example.proxy.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RequestHandler {

    private RequestMapper requestMapper;
    private RequestService requestService;


    public ResponseEntity<?> create(RequestDto requestDto) {
        try {
            Request request = requestMapper.toRequest(requestDto);
            requestService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SQLException(ex));
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

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<Request> requests = requestService.getAll(pageNo, pageSize);
        List<Request> requestList = requests.getContent();
        List<RequestDto> content= requestList.stream().map(request ->  requestMapper.toRequestDto(request)).collect(Collectors.toList());
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(content);
        paginationResponse.setPageNo(requests.getNumber()+1);
        paginationResponse.setPageSize(requests.getSize());
        paginationResponse.setTotalElements(requests.getTotalElements());
        paginationResponse.setTotalPages(requests.getTotalPages());

        return ResponseEntity.ok(paginationResponse);
    }

    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
        Request request = requestService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("request of id " + id + " Not Found"));
        requestService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }
}
