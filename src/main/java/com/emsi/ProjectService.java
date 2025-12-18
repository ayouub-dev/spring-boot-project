package com.emsi;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Project not found: " + id));
    }

    public void createProject(Project project) {
        // Intentional code smell: No validation
        projectRepository.save(project);
    }

    public void updateProjectStatus(Integer id, String status) {
        Project project = getProjectById(id);
        project.setStatus(status);

        if ("STARTED".equals(status)) {
            project.setStartDate(new Date());
        } else if ("COMPLETED".equals(status)) {
            project.setEndDate(new Date());
        }

        projectRepository.save(project);
    }

    public List<Project> getProjectsByEngineer(Integer engineerId) {
        return projectRepository.findByEngineerId(engineerId);
    }

    public List<Project> searchProjects(String name) {
        return projectRepository.searchByName(name);
    }

    public void deleteProject(Integer id) {
        Project project = getProjectById(id);
        projectRepository.delete(project);
    }
}

