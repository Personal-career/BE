package com.ssu10.personal_career.controller;

import com.ssu10.personal_career.domain.Project;
import com.ssu10.personal_career.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
@CrossOrigin(origins = "http://localhost:3000")
public class PortfolioController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getProjects(@RequestParam(required = false) Long userId) {
        return projectService.getProjectsByMemberId(1L); //test용 상수
    }


    // 프로젝트 단건 조회
    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    // 프로젝트 생성
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        if (project.getMember_id() == null) {
            project.setMember_id(1L); // 테스트용 기본 member_id, 나중에 로그인 사용자 정보로 교체
        }
        return projectService.saveProject(project);
    }

    // 프로젝트 수정
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        return projectService.updateProject(id, updatedProject);
    }

    // 프로젝트 삭제
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
