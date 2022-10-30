package com.example.proxy.repository;

import com.example.proxy.model.RequestDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestDocumentRepository extends JpaRepository<RequestDocument, Long> {

    @Query(value = "SELECT u FROM RequestDocument u WHERE u.name =:name")
    RequestDocument findDocumentByName(String name);

    @Query(value = "SELECT u FROM RequestDocument u WHERE u.description LIKE %:description%")
    List<RequestDocument> searchByDescription(String description);

}

