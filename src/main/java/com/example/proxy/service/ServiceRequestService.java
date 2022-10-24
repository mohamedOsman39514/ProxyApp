package com.example.proxy.service;

import com.example.proxy.model.Request;
import com.example.proxy.model.ServiceRequest;
import com.example.proxy.repository.RequestRepository;
import com.example.proxy.repository.ServiceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;


    public ServiceRequest save(ServiceRequest serviceRequest) {
        return serviceRequestRepository.save(serviceRequest);
    }

    public Optional<ServiceRequest> findById(Long id) {
        return serviceRequestRepository.findById(id);
    }

    public List<ServiceRequest> findAll()
    {
        return serviceRequestRepository.findAll();
    }

    public void deleteById(Long id)
    {
        serviceRequestRepository.deleteById(id);
    }

}
