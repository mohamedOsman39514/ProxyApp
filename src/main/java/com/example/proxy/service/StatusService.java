package com.example.proxy.service;

import com.example.proxy.model.ServiceDefinition;
import com.example.proxy.model.ServiceType;
import com.example.proxy.model.Status;
import com.example.proxy.repository.ServiceDefinitionRepository;
import com.example.proxy.repository.StatusRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StatusService {

//    @Autowired
    private StatusRepository statusRepository;


//    public Status save(Status status) {
//        return statusRepository.save(status);
//    }

    public Optional<Status> findById(Long id) {
        return statusRepository.findById(id);
    }

    public Page<Status> getAll(Integer page, Integer size) {
        return statusRepository.findAll(PageRequest.of(page, size));
    }
//    public void deleteById(Long id)
//    {
//        statusRepository.deleteById(id);
//    }

}
