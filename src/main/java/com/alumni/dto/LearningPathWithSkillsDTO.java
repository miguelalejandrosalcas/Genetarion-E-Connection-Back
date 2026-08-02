package com.alumni.dto;

import com.alumni.model.LearningPath;

import java.util.List;
import java.util.stream.Collectors;

public class LearningPathWithSkillsDTO {
    private Long id;
    private String name;
    private String description;
    private boolean active;
    private List<SkillDTO> skills;

    public LearningPathWithSkillsDTO() {
    }

    public LearningPathWithSkillsDTO(Long id, String name, String description, boolean active, List<SkillDTO> skills) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    public static LearningPathWithSkillsDTO fromEntity(LearningPath learningPath) {
        List<SkillDTO> skillDTOs = learningPath.getSkills().stream()
                .map(SkillDTO::fromEntity)
                .collect(Collectors.toList());
        return new LearningPathWithSkillsDTO(
                learningPath.getId(),
                learningPath.getName(),
                learningPath.getDescription(),
                learningPath.isActive(),
                skillDTOs
        );
    }
}