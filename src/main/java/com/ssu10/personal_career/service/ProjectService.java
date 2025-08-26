package com.ssu10.personal_career.service;

import com.ssu10.personal_career.domain.Member;
import com.ssu10.personal_career.domain.Project;
import com.ssu10.personal_career.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }
/*
    public List<Project> findByMember(Member member) {
        return projectRepository.findByMember(member);
    }*/

    public List<Project> getProjectsByMemberId(Long memberId) {
        return projectRepository.findByMember_id(memberId);
    }

}
