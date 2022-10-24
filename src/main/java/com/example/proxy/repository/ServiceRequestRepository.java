package com.example.proxy.repository;

import com.example.proxy.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
}
