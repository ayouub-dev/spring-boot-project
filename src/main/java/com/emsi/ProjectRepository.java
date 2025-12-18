package com.emsi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByEngineerId(Integer engineerId);

    List<Project> findByStatus(String status);

    @Query("SELECT p FROM Project p WHERE p.name LIKE %?1%")
    List<Project> searchByName(String name);
}

