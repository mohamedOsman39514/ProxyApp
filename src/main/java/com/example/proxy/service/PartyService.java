package com.example.proxy.service;

import com.example.proxy.model.Party;
import com.example.proxy.repository.PartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PartyService {

    private PartyRepository partyRepository;

    public Optional<Party> findById(Long id) {
        return partyRepository.findById(id);
    }

    public List<Party> findAll()
    {
        return partyRepository.findAll();
    }

}
