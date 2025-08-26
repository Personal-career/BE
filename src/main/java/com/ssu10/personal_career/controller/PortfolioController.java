package com.ssu10.personal_career.controller;

import com.ssu10.personal_career.domain.Project;
import com.ssu10.personal_career.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getProjects(@RequestParam(required = false) Long userId) {
        return projectService.getProjectsByMemberId(1L); //test용 상수
    }




}
