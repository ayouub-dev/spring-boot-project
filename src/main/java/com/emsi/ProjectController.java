package com.emsi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Integer id) {
        // Intentional code smell: No error handling
        return projectService.getProjectById(id);
    }

    @PostMapping
    public void createProject(@RequestBody Project project) {
        // Intentional code smell: No response entity, no validation
        projectService.createProject(project);
    }

    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable Integer id, @RequestParam String status) {
        // Intentional code smell: No validation
        projectService.updateProjectStatus(id, status);
    }

    @GetMapping("/engineer/{engineerId}")
    public List<Project> getProjectsByEngineer(@PathVariable Integer engineerId) {
        return projectService.getProjectsByEngineer(engineerId);
    }

    @GetMapping("/search")
    public List<Project> searchProjects(@RequestParam String name) {
        // Intentional security issue: No input validation
        return projectService.searchProjects(name);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
    }

    // Intentional code smell: Exception handling with printStackTrace
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace(); // Code smell: printStackTrace usage
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
    }
}

