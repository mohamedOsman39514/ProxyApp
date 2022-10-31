package com.example.proxy.rest.handler;

import com.example.proxy.model.Job;
import com.example.proxy.rest.dto.JobDto;
import com.example.proxy.rest.dto.common.PaginationResponse;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.mapper.JobMapper;
import com.example.proxy.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<Job> jobs = jobService.getAll(pageNo, pageSize);
        List<Job> jobList = jobs.getContent();
        List<JobDto> content= jobList.stream().map(job ->  jobMapper.toJobDto(job)).collect(Collectors.toList());
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(content);
        paginationResponse.setPageNo(jobs.getNumber()+1);
        paginationResponse.setPageSize(jobs.getSize());
        paginationResponse.setTotalElements(jobs.getTotalElements());
        paginationResponse.setTotalPages(jobs.getTotalPages());

        return ResponseEntity.ok(paginationResponse);
    }
}
