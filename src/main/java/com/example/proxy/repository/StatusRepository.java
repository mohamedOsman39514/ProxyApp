package com.example.proxy.repository;

import com.example.proxy.model.Status;
import com.example.proxy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
