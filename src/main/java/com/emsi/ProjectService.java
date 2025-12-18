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
        // Intentional code smell: No null check or proper exception handling
        return projectRepository.findById(id).get();
    }

    public void createProject(Project project) {
        // Intentional code smell: No validation
        projectRepository.save(project);
    }

    // Intentional code smell: Method too long with duplicated code
    public void updateProjectStatus(Integer id, String status) {
        Project project = projectRepository.findById(id).get();
        if (status.equals("STARTED")) {
            project.setStatus("STARTED");
            project.setStartDate(new Date());
            projectRepository.save(project);
        } else if (status.equals("IN_PROGRESS")) {
            project.setStatus("IN_PROGRESS");
            projectRepository.save(project);
        } else if (status.equals("COMPLETED")) {
            project.setStatus("COMPLETED");
            project.setEndDate(new Date());
            projectRepository.save(project);
        } else if (status.equals("CANCELLED")) {
            project.setStatus("CANCELLED");
            projectRepository.save(project);
        } else if (status.equals("ON_HOLD")) {
            project.setStatus("ON_HOLD");
            projectRepository.save(project);
        }
    }

    public List<Project> getProjectsByEngineer(Integer engineerId) {
        return projectRepository.findByEngineerId(engineerId);
    }

    public List<Project> searchProjects(String name) {
        // Intentional security issue: No input sanitization
        return projectRepository.searchByName(name);
    }

    // Intentional code smell: Unused method
    private void unusedMethod() {
        System.out.println("This method is never called");
    }

    // Intentional code duplication
    public void deleteProject(Integer id) {
        Project project = projectRepository.findById(id).get();
        projectRepository.delete(project);
    }

    public void removeProject(Integer id) {
        Project project = projectRepository.findById(id).get();
        projectRepository.delete(project);
    }
}

