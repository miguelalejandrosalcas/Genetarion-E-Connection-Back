package com.alumni.dto;

import com.alumni.model.LearningPath;
import com.alumni.model.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LearningPathDTO {
    private Long id;
    private String name;
    private String description;
    private Long programId;
    private boolean active;
    private List<Long> skillIds = new ArrayList<>();

    public LearningPathDTO() {
    }

    public LearningPathDTO(Long id, String name, String description, Long programId,
                           boolean active, List<Long> skillIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.programId = programId;
        this.active = active;
        this.skillIds = skillIds;
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

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Long> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Long> skillIds) {
        this.skillIds = skillIds;
    }

    public static LearningPathDTO fromEntity(LearningPath learningPath) {
        return new LearningPathDTO(
                learningPath.getId(),
                learningPath.getName(),
                learningPath.getDescription(),
                learningPath.getProgram() != null ? learningPath.getProgram().getId() : null,
                learningPath.isActive(),
                learningPath.getSkills() != null
                        ? learningPath.getSkills().stream().map(Skill::getId).collect(Collectors.toList())
                        : new ArrayList<>()
        );
    }

    public LearningPath toEntity() {
        LearningPath learningPath = new LearningPath();
        learningPath.setName(this.name);
        learningPath.setDescription(this.description);
        learningPath.setActive(this.active);
        return learningPath;
    }
}