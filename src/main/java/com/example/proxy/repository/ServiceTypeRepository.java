package com.example.proxy.repository;

import com.example.proxy.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
}
