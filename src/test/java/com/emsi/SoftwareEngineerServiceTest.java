package com.emsi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SoftwareEngineerServiceTest {

    @Mock
    private SoftwareEngineerRepository softwareEngineerRepository;

    @InjectMocks
    private SoftwareEngineerService softwareEngineerService;

    private SoftwareEngineer testEngineer;

    @BeforeEach
    void setUp() {
        testEngineer = new SoftwareEngineer(1, "John Doe", "Java, Spring Boot");
    }

    @Test
    void getAllSoftwareEngineers_ShouldReturnAllEngineers() {
        // Arrange
        List<SoftwareEngineer> engineers = Arrays.asList(
                testEngineer,
                new SoftwareEngineer(2, "Jane Smith", "Python, Django")
        );
        when(softwareEngineerRepository.findAll()).thenReturn(engineers);

        // Act
        List<SoftwareEngineer> result = softwareEngineerService.getAllSoftwareEngineeer();

        // Assert
        assertEquals(2, result.size());
        verify(softwareEngineerRepository, times(1)).findAll();
    }

    @Test
    void insertSoftwareEngineer_ShouldSaveEngineer() {
        // Arrange
        when(softwareEngineerRepository.save(testEngineer)).thenReturn(testEngineer);

        // Act
        softwareEngineerService.insertSoftwareEngineer(testEngineer);

        // Assert
        verify(softwareEngineerRepository, times(1)).save(testEngineer);
    }

    @Test
    void getSoftwareEngineerById_WhenExists_ShouldReturnEngineer() {
        // Arrange
        when(softwareEngineerRepository.findById(1)).thenReturn(Optional.of(testEngineer));

        // Act
        SoftwareEngineer result = softwareEngineerService.getSoftwareEngineeerById(1);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(softwareEngineerRepository, times(1)).findById(1);
    }

    @Test
    void getSoftwareEngineerById_WhenNotExists_ShouldThrowException() {
        // Arrange
        when(softwareEngineerRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            softwareEngineerService.getSoftwareEngineeerById(999);
        });
    }
}

