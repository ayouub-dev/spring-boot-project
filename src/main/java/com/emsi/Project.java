package com.emsi;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String status;
    private Date startDate;
    private Date endDate;
    private Integer engineerId;

    // Intentional code smell: Empty constructor without purpose
    public Project() {
    }

    public Project(Integer id, String name, String description, String status, Date startDate, Date endDate, Integer engineerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.engineerId = engineerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    // Intentional bug: Mutable date object exposure
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    // Intentional bug: Mutable date object exposure
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(Integer engineerId) {
        this.engineerId = engineerId;
    }

    // Intentional code smell: No equals/hashCode implementation
}

