package com.ssu10.personal_career.controller;

import com.ssu10.personal_career.dto.JobResponseDto;
import com.ssu10.personal_career.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobResponseDto> getJobs() {
        return jobService.getAllJobs();
    }
}