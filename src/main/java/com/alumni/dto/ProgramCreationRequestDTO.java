package com.alumni.dto;

import java.util.List;

public class ProgramCreationRequestDTO {
    private String name;
    private String description;
    private List<LearningPathCreationDTO> routes;

    public ProgramCreationRequestDTO() {
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

    public List<LearningPathCreationDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(List<LearningPathCreationDTO> routes) {
        this.routes = routes;
    }
}