package com.example.proxy.service;

import com.example.proxy.model.Governorate;
import com.example.proxy.repository.GovernorateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GovernorateService {

    private GovernorateRepository governorateRepository;

    public Optional<Governorate> findById(Long id) {
        return governorateRepository.findById(id);
    }

    public List<Governorate> findAll()
    {
        return governorateRepository.findAll();
    }

}
