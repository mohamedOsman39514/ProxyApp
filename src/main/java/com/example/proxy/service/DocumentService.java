package com.example.proxy.service;

import com.example.proxy.model.Document;
import com.example.proxy.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;


    public Document save(Document document) {
        return documentRepository.save(document);
    }

    public Optional<Document> findById(Long id) {
        return documentRepository.findById(id);
    }

    public List<Document> findAll()
    {
        return documentRepository.findAll();
    }

    public void deleteById(Long id)
    {
        documentRepository.deleteById(id);
    }

}
