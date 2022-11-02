package com.example.proxy.repository;

import com.example.proxy.entity.RequestDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestDocumentRepository extends JpaRepository<RequestDocument, Long> {

    @Query(value = "SELECT d FROM RequestDocument d WHERE d.name =:name")
    RequestDocument findDocumentByName(String name);

    @Query(value = "SELECT d FROM RequestDocument d WHERE d.description LIKE %:description%")
    List<RequestDocument> searchByDescription(String description);

}

