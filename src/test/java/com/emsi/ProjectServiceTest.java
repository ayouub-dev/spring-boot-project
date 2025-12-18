package com.emsi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project testProject;

    @BeforeEach
    void setUp() {
        testProject = new Project(1, "Test Project", "Description", "STARTED",
                new Date(), null, 1);
    }

    @Test
    void getAllProjects_ShouldReturnAllProjects() {
        // Arrange
        List<Project> projects = Arrays.asList(testProject);
        when(projectRepository.findAll()).thenReturn(projects);

        // Act
        List<Project> result = projectService.getAllProjects();

        // Assert
        assertEquals(1, result.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void getProjectById_ShouldReturnProject() {
        // Arrange
        when(projectRepository.findById(1)).thenReturn(Optional.of(testProject));

        // Act
        Project result = projectService.getProjectById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Test Project", result.getName());
    }

    @Test
    void createProject_ShouldSaveProject() {
        // Arrange
        when(projectRepository.save(testProject)).thenReturn(testProject);

        // Act
        projectService.createProject(testProject);

        // Assert
        verify(projectRepository, times(1)).save(testProject);
    }

    @Test
    void getProjectsByEngineer_ShouldReturnProjectsForEngineer() {
        // Arrange
        List<Project> projects = Arrays.asList(testProject);
        when(projectRepository.findByEngineerId(1)).thenReturn(projects);

        // Act
        List<Project> result = projectService.getProjectsByEngineer(1);

        // Assert
        assertEquals(1, result.size());
        verify(projectRepository, times(1)).findByEngineerId(1);
    }
}

