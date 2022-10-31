package com.example.proxy.rest.handler;

import com.example.proxy.model.Party;
import com.example.proxy.rest.dto.PartyDto;
import com.example.proxy.rest.dto.common.PaginationResponse;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.mapper.PartyMapper;
import com.example.proxy.service.PartyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<Party> parties = partyService.getAllParties(pageNo, pageSize);
        List<Party> partyList = parties.getContent();
        List<PartyDto> content= partyList.stream().map(party ->  partyMapper.toPartyDto(party)).collect(Collectors.toList());
        PaginationResponse postResponse = new PaginationResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(parties.getNumber()+1);
        postResponse.setPageSize(parties.getSize());
        postResponse.setTotalElements(parties.getTotalElements());
        postResponse.setTotalPages(parties.getTotalPages());

        return ResponseEntity.ok(postResponse);
    }

}
