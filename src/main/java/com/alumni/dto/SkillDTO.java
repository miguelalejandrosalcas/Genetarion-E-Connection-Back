package com.alumni.dto;

import com.alumni.model.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillDTO {
    private Long id;
    private String skillName;
    private String description;
    private List<String> links = new ArrayList<>();

    public SkillDTO() {
    }

    public SkillDTO(Long id, String skillName, String description, List<String> links) {
        this.id = id;
        this.skillName = skillName;
        this.description = description;
        this.links = links != null ? links : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getLinks() { return links; }
    public void setLinks(List<String> links) { this.links = links; }

    public static SkillDTO fromEntity(Skill skill) {
        return new SkillDTO(
                skill.getId(),
                skill.getSkillName(),
                skill.getDescription(),
                skill.getLinks()
        );
    }

    public Skill toEntity() {
        Skill skill = new Skill();
        skill.setSkillName(this.skillName);
        skill.setDescription(this.description);
        skill.setLinks(this.links != null ? this.links : new ArrayList<>());
        return skill;
    }
}