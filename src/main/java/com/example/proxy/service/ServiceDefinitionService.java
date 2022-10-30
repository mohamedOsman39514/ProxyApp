package com.example.proxy.service;

import com.example.proxy.model.ServiceDefinition;
import com.example.proxy.model.ServiceRequest;
import com.example.proxy.repository.ServiceDefinitionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceDefinitionService {

//    @Autowired
    private ServiceDefinitionRepository serviceDefinitionRepository;


//    public ServiceDefinition save(ServiceDefinition serviceDefinition) {
//        return serviceDefinitionRepository.save(serviceDefinition);
//    }

    public Optional<ServiceDefinition> findById(Long id) {
        return serviceDefinitionRepository.findById(id);
    }

    public List<ServiceDefinition> findAll()
    {
        return serviceDefinitionRepository.findAll();
    }

//    public void deleteById(Long id)
//    {
//        serviceDefinitionRepository.deleteById(id);
//    }

}
