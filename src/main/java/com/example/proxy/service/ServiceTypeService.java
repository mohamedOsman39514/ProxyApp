package com.example.proxy.service;

import com.example.proxy.model.ServiceType;
import com.example.proxy.repository.ServiceTypeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceTypeService {

//    @Autowired
    private ServiceTypeRepository serviceTypeRepository;


//    public ServiceType save(ServiceType serviceType) {
//        return serviceTypeRepository.save(serviceType);
//    }

    public Optional<ServiceType> findById(Long id) {
        return serviceTypeRepository.findById(id);
    }

    public List<ServiceType> findAll()
    {
        return serviceTypeRepository.findAll();
    }

//    public void deleteById(Long id)
//    {
//        serviceTypeRepository.deleteById(id);
//    }

}
