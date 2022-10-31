package com.example.proxy.service;

import com.example.proxy.model.Request;
import com.example.proxy.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestService {

    private RequestRepository requestRepository;


    public Request save(Request request) {
        return requestRepository.save(request);
    }

    public Optional<Request> findById(Long id) {
        return requestRepository.findById(id);
    }

    public Page<Request> getAll(Integer page, Integer size) {
        return requestRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteById(Long id)
    {
        requestRepository.deleteById(id);
    }

}
