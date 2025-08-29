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

    public List<Project> getProjectsByMemberId(Long memberId) {
        return projectRepository.findByMember_id(memberId);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("프로젝트를 찾을 수 없습니다. id=" + id));
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project updatedProject) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("프로젝트를 찾을 수 없습니다. id=" + id));

        existing.setName(updatedProject.getName());
        existing.setPeriod(updatedProject.getPeriod());
        existing.setType(updatedProject.getType());
        existing.setDescription(updatedProject.getDescription());
        existing.setTechStack(updatedProject.getTechStack());

        return projectRepository.save(existing);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("삭제할 프로젝트가 없습니다. id=" + id);
        }
        projectRepository.deleteById(id);
    }
}
