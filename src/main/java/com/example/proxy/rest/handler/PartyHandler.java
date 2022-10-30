package com.example.proxy.rest.handler;

import com.example.proxy.model.Governorate;
import com.example.proxy.model.Party;
import com.example.proxy.rest.dto.GovernorateDto;
import com.example.proxy.rest.dto.PartyDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.mapper.GovernorateMapper;
import com.example.proxy.rest.mapper.PartyMapper;
import com.example.proxy.service.GovernorateService;
import com.example.proxy.service.PartyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PartyHandler {

    private PartyService partyService;
    private PartyMapper partyMapper;

    public ResponseEntity<?> getById(Long id) throws ResourceNotFound {
        Party party = partyService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The party of id " + id + " Not Found"));
        PartyDto partyDto = partyMapper.toPartyDto(party);
        return ResponseEntity.ok(partyDto);
    }

    public ResponseEntity<List<?>> getAll() {
        List<?> list = partyMapper.toPartyDtos(partyService.findAll());
        return ResponseEntity.ok(list);
    }
}
