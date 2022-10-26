package com.example.proxy.repository;

import com.example.proxy.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT u FROM Document u WHERE u.name =:name")
    Document findDocumentByName(String name);
}
