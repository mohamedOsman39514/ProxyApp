package com.example.proxy.service;

import com.example.proxy.model.RequestDocument;
import com.example.proxy.repository.RequestDocumentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestDocumentService {

    private RequestDocumentRepository requestDocumentRepository;


    public RequestDocument save(RequestDocument document) {
        return requestDocumentRepository.save(document);
    }

    public RequestDocument findDocumentByName(String name) {
        return requestDocumentRepository.findDocumentByName(name);
    }

    public List<RequestDocument> searchDocumentByDescription(String description){
        return requestDocumentRepository.searchByDescription(description);
    }

    public Optional<RequestDocument> findById(Long id) {
        return requestDocumentRepository.findById(id);
    }

    public List<RequestDocument> findAll()
    {
        return requestDocumentRepository.findAll();
    }

    public void deleteById(Long id)
    {
        requestDocumentRepository.deleteById(id);
    }

}
