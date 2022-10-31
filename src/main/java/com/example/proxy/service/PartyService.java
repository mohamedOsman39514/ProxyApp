package com.example.proxy.service;

import com.example.proxy.model.Party;
import com.example.proxy.repository.PartyRepository;
import com.example.proxy.rest.dto.PartyDto;
import com.example.proxy.rest.dto.common.PaginationResponse;
import com.example.proxy.rest.mapper.PartyMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PartyService {

    private PartyRepository partyRepository;

    public Optional<Party> findById(Long id) {
        return partyRepository.findById(id);
    }
    
    public Page<Party> getAllParties(Integer page, Integer size) {
        return partyRepository.findAll(PageRequest.of(page, size));
    }

}
