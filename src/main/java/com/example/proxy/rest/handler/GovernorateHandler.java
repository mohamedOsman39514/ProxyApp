package com.example.proxy.rest.handler;

import com.example.proxy.model.Governorate;
import com.example.proxy.rest.dto.GovernorateDto;
import com.example.proxy.rest.dto.common.PaginationResponse;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.mapper.GovernorateMapper;
import com.example.proxy.service.GovernorateService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GovernorateHandler {

    private GovernorateService governorateService;
    private GovernorateMapper governorateMapper;

    public ResponseEntity<?> getById(Long id) throws ResourceNotFound {
        Governorate governorate = governorateService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The governorate of id " + id + " Not Found"));
        GovernorateDto governorateDto = governorateMapper.toGovernorateDto(governorate);
        return ResponseEntity.ok(governorateDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<Governorate> governorates = governorateService.getAll(pageNo, pageSize);
        List<Governorate> governorateList = governorates.getContent();
        List<GovernorateDto> content= governorateList.stream().map(governorate -> governorateMapper.toGovernorateDto(governorate)).collect(Collectors.toList());
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(content);
        paginationResponse.setPageNo(governorates.getNumber()+1);
        paginationResponse.setPageSize(governorates.getSize());
        paginationResponse.setTotalElements(governorates.getTotalElements());
        paginationResponse.setTotalPages(governorates.getTotalPages());

        return ResponseEntity.ok(paginationResponse);
    }

}
