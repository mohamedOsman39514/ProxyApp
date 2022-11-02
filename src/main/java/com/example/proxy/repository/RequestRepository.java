package com.example.proxy.repository;

import com.example.proxy.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(value = "SELECT r FROM Request r WHERE r.status.name= :statusName")
    Page<Request> findByStatus(@Param("statusName") String statusName, Pageable pageable);

}
