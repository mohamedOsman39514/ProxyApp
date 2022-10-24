package com.example.proxy.repository;

import com.example.proxy.model.ServiceDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceDefinitionRepository extends JpaRepository<ServiceDefinition, Long> {
}
