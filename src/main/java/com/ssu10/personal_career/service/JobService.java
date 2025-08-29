package com.ssu10.personal_career.service;

import com.ssu10.personal_career.dto.JobResponseDto;
import com.ssu10.personal_career.domain.Job;
import com.ssu10.personal_career.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobResponseDto> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(JobResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
