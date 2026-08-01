package com.alumni.dto;

import com.alumni.model.Skill;

public class SkillDTO {
    private Long id;
    private String skillName;
    private String description;

    public SkillDTO() {
    }

    public SkillDTO(Long id, String skillName, String description) {
        this.id = id;
        this.skillName = skillName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static SkillDTO fromEntity(Skill skill) {
        return new SkillDTO(
                skill.getId(),
                skill.getSkillName(),
                skill.getDescription()
        );
    }

    // Skill solo tiene constructor vacio, así que se arma con setters
    public Skill toEntity() {
        Skill skill = new Skill();
        skill.setSkillName(this.skillName);
        skill.setDescription(this.description);
        return skill;
    }
}