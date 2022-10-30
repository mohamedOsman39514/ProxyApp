package com.example.proxy.service;

import com.example.proxy.model.Job;
import com.example.proxy.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JobService {

    private JobRepository jobRepository;

    public Optional<Job> findById(Long id) {
        return jobRepository.findById(id);
    }

    public List<Job> findAll()
    {
        return jobRepository.findAll();
    }

}
