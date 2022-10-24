package com.example.proxy.service;

import com.example.proxy.model.Request;
import com.example.proxy.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;


    public Request save(Request request) {
        return requestRepository.save(request);
    }

    public Optional<Request> findById(Long id) {
        return requestRepository.findById(id);
    }

    public List<Request> findAll()
    {
        return requestRepository.findAll();
    }

    public void deleteById(Long id)
    {
        requestRepository.deleteById(id);
    }

}
