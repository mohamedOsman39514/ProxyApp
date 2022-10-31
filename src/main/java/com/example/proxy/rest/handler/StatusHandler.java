package com.example.proxy.rest.handler;

import com.example.proxy.model.Status;
import com.example.proxy.rest.dto.StatusDto;
import com.example.proxy.rest.dto.common.PaginationResponse;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.mapper.StatusMapper;
import com.example.proxy.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StatusHandler {

    private StatusMapper statusMapper;
    private StatusService statusService;

//    private PSQLException psqlException;

//    public ResponseEntity<?> create(StatusDto statusDto) {
//        try {
//            Status status = statusMapper.toStatus(statusDto);
//            statusService.save(status);
//            return ResponseEntity.status(HttpStatus.CREATED).body(status);
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(
//                    new Response(psqlException.getError(ex)));
//        }
//    }
//
//    public ResponseEntity<?> update(Long id, StatusDto statusDto) throws ResourceNotFound {
//        Status status = statusMapper.toStatus(statusDto);
//        Status statusById = statusService.findById(id)
//                .orElseThrow(() -> new ResourceNotFound("status of id " + id + " Not Found"));
//        statusById.setName(status.getName() != null ? status.getName() : statusById.getName());
//        statusService.save(statusById);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(statusById);
//    }

    public ResponseEntity<?> getById(Long id)
            throws ResourceNotFound {
        Status status = statusService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("status of id " + id + " Not Found"));
        StatusDto statusDto = statusMapper.toStatusDto(status);
        return ResponseEntity.ok(statusDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<Status> statuses = statusService.getAll(pageNo, pageSize);
        List<Status> statusList = statuses.getContent();
        List<StatusDto> content= statusList.stream().map(status ->  statusMapper.toStatusDto(status)).collect(Collectors.toList());
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(content);
        paginationResponse.setPageNo(statuses.getNumber()+1);
        paginationResponse.setPageSize(statuses.getSize());
        paginationResponse.setTotalElements(statuses.getTotalElements());
        paginationResponse.setTotalPages(statuses.getTotalPages());

        return ResponseEntity.ok(paginationResponse);
    }

//    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
//        Status statusById = statusService.findById(id)
//                .orElseThrow(() -> new ResourceNotFound("status of id " + id + " Not Found"));
//        statusService.deleteById(id);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
//    }

}
