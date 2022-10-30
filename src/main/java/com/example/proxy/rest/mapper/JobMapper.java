package com.example.proxy.rest.mapper;

import com.example.proxy.model.Job;
import com.example.proxy.rest.dto.JobDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {

    List<JobDto> toJobDtos(List<Job> jobs);

    Job toJob(JobDto jobDto);

    JobDto toJobDto(Job job);

}
