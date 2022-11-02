package com.example.proxy.service;

import com.example.proxy.entity.Status;
import com.example.proxy.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StatusService {

//    @Autowired
    private StatusRepository statusRepository;


//    public Status save(Status status) {
//        return statusRepository.save(status);
//    }

    public Optional<Status> findById(Long id) {
        return statusRepository.findById(id);
    }

    public Page<Status> getAll(Integer page, Integer size) {
        return statusRepository.findAll(PageRequest.of(page, size));
    }
//    public void deleteById(Long id)
//    {
//        statusRepository.deleteById(id);
//    }

}
