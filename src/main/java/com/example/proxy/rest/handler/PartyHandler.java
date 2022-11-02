package com.example.proxy.rest.handler;

import com.example.proxy.entity.Party;
import com.example.proxy.rest.dto.PartyDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.entitymapper.PartyMapper;
import com.example.proxy.rest.exception.ResourceNotFoundException;
import com.example.proxy.service.PartyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PartyHandler {

    private PartyService partyService;
    private PartyMapper partyMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getById(Long id) {
        Party party = partyService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Party.class.getSimpleName(),id));
        PartyDto partyDto = partyMapper.toDto(party);
        return ResponseEntity.ok(partyDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<Party> parties = partyService.getAllParties(pageNo, pageSize);
        List<PartyDto> content= partyMapper.toDto(parties.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(parties));
        return ResponseEntity.ok(paginatedResult);
    }

}
