package com.example.proxy.rest.handler;

import com.example.proxy.model.Governorate;
import com.example.proxy.rest.dto.GovernorateDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.mapper.GovernorateMapper;
import com.example.proxy.service.GovernorateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ResponseEntity<List<?>> getAll() {
        List<?> list = governorateMapper.toGovernorateDtos(governorateService.findAll());
        return ResponseEntity.ok(list);
    }

}
