package com.example.proxy.service;

import com.example.proxy.model.Request;
import com.example.proxy.model.ServiceDefinition;
import com.example.proxy.model.ServiceRequest;
import com.example.proxy.repository.RequestRepository;
import com.example.proxy.repository.ServiceRequestRepository;
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
public class ServiceRequestService {

//    @Autowired
    private ServiceRequestRepository serviceRequestRepository;


    public ServiceRequest save(ServiceRequest serviceRequest) {
        return serviceRequestRepository.save(serviceRequest);
    }

    public Optional<ServiceRequest> findById(Long id) {
        return serviceRequestRepository.findById(id);
    }

    public Page<ServiceRequest> getAll(Integer page, Integer size) {
        return serviceRequestRepository.findAll(PageRequest.of(page, size));
    }
    public void deleteById(Long id)
    {
        serviceRequestRepository.deleteById(id);
    }

}
