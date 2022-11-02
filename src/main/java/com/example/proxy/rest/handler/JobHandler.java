package com.example.proxy.rest.handler;

import com.example.proxy.entity.Job;
import com.example.proxy.rest.dto.JobDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.entitymapper.JobMapper;
import com.example.proxy.rest.exception.ResourceNotFoundException;
import com.example.proxy.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class JobHandler {

    private JobService jobService;
    private JobMapper jobMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getById(Long id) {
        Job job = jobService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Job.class.getSimpleName(),id));
        JobDto jobDto = jobMapper.toDto(job);
        return ResponseEntity.ok(jobDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize) {
        Page<Job> jobs = jobService.getAll(pageNo, pageSize);
        List<JobDto> content = jobMapper.toDto(jobs.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(jobs));
        return ResponseEntity.ok(paginatedResult);
    }

}
