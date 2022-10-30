package com.example.proxy.rest.handler;

import com.example.proxy.model.Governorate;
import com.example.proxy.model.Job;
import com.example.proxy.rest.dto.GovernorateDto;
import com.example.proxy.rest.dto.JobDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.mapper.GovernorateMapper;
import com.example.proxy.rest.mapper.JobMapper;
import com.example.proxy.service.GovernorateService;
import com.example.proxy.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class JobHandler {

    private JobService jobService;
    private JobMapper jobMapper;

    public ResponseEntity<?> getById(Long id) throws ResourceNotFound {
        Job job = jobService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The job of id " + id + " Not Found"));
        JobDto jobDto = jobMapper.toJobDto(job);
        return ResponseEntity.ok(jobDto);
    }

    public ResponseEntity<List<?>> getAll() {
        List<?> list = jobMapper.toJobDtos(jobService.findAll());
        return ResponseEntity.ok(list);
    }
}
