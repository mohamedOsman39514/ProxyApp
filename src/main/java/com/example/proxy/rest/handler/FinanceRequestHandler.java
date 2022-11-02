//package com.example.proxy.rest.handler;
//
//import com.example.proxy.entity.*;
//import com.example.proxy.rest.dto.FinanceRequestDto;
//import com.example.proxy.rest.dto.common.PaginationResponseDto;
//import com.example.proxy.rest.exception.SQLException;
//import com.example.proxy.rest.exception.ResourceNotFound;
//import com.example.proxy.rest.exception.Response;
//import com.example.proxy.rest.entitymapper.FinanceRequestMapper;
//import com.example.proxy.service.RequestService;
//import com.example.proxy.service.FinanceRequestService;
//import com.example.proxy.service.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@AllArgsConstructor
//public class FinanceRequestHandler {
//
//    private FinanceRequestMapper financeRequestMapper;
//    private FinanceRequestService financeRequestService;
//    private RequestService requestService;
//    private UserService userService;
//
//
//    public ResponseEntity<?> create(FinanceRequestDto financeRequestDto) {
//        try {
//            String email = SecurityContextHolder.getContext().getAuthentication().getName();
//            User user = userService.findByEmail(email);
//            FinanceRequest financeRequest = financeRequestMapper.toEntity(financeRequestDto);
//            Request request = requestService.findById(financeRequest.getRequest().getId()).get();
//            financeRequestService.save(financeRequest);
//            return ResponseEntity.status(HttpStatus.CREATED).body(financeRequest);
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SQLException(ex));
//        }
//    }
//
//    public ResponseEntity<?> update(Long id, FinanceRequestDto financeRequestDto) throws ResourceNotFound {
//        FinanceRequest financeRequest = financeRequestMapper.toEntity(financeRequestDto);
//        FinanceRequest financeRequestById = financeRequestService.findById(id)
//                .orElseThrow(() -> new ResourceNotFound("service request of id " + id + " Not Found"));
//        financeRequestById.setAmount(financeRequest.getAmount() != null ? financeRequest.getAmount() : financeRequestById.getAmount());
//        financeRequestById.setStartDate(financeRequest.getStartDate() != null ? financeRequest.getStartDate() : financeRequestById.getStartDate());
//        financeRequestById.setEndDate(financeRequest.getEndDate() != null ? financeRequest.getEndDate() : financeRequestById.getEndDate());
//        financeRequestService.save(financeRequestById);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(financeRequestById);
//    }
//
//    public ResponseEntity<?> getById(Long id)
//            throws ResourceNotFound {
//        FinanceRequest financeRequest = financeRequestService.findById(id)
//                .orElseThrow(() -> new ResourceNotFound("The service request of id " + id + " Not Found"));
//        FinanceRequestDto financeRequestDto = financeRequestMapper.toDto(financeRequest);
//        return ResponseEntity.ok(financeRequestDto);
//    }
//
//    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
//        Page<FinanceRequest> requests = financeRequestService.getAll(pageNo, pageSize);
//        List<FinanceRequest> requestList = requests.getContent();
//        List<FinanceRequestDto> content= requestList.stream().map(serviceRequest ->  financeRequestMapper.toDto(serviceRequest)).collect(Collectors.toList());
//        PaginationResponseDto paginationResponseDto = new PaginationResponseDto();
//        paginationResponseDto.setContent(content);
//        paginationResponseDto.setPageNo(requests.getNumber()+1);
//        paginationResponseDto.setPageSize(requests.getSize());
//        paginationResponseDto.setTotalElements(requests.getTotalElements());
//        paginationResponseDto.setTotalPages(requests.getTotalPages());
//
//        return ResponseEntity.ok(paginationResponseDto);
//    }
//
//    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
//        FinanceRequest financeRequest = financeRequestService.findById(id)
//                .orElseThrow(() -> new ResourceNotFound("service request of id " + id + " Not Found"));
//        financeRequestService.deleteById(id);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
//    }
//}
