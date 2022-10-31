package com.example.proxy.service;

import com.example.proxy.model.ServiceRequest;
import com.example.proxy.model.ServiceType;
import com.example.proxy.repository.ServiceTypeRepository;
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
public class ServiceTypeService {

    private ServiceTypeRepository serviceTypeRepository;


//    public ServiceType save(ServiceType serviceType) {
//        return serviceTypeRepository.save(serviceType);
//    }

    public Optional<ServiceType> findById(Long id) {
        return serviceTypeRepository.findById(id);
    }

    public Page<ServiceType> getAll(Integer page, Integer size) {
        return serviceTypeRepository.findAll(PageRequest.of(page, size));
    }
//    public void deleteById(Long id)
//    {
//        serviceTypeRepository.deleteById(id);
//    }

}
