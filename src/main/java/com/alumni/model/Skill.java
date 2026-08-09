package com.alumni.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name", length = 150, nullable = false)
    private String skillName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "skills")
    private List<LearningPath> learningPaths = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "skill_links", joinColumns = @JoinColumn(name = "id_skills"))
    @Column(name = "link", length = 2000)
    private List<String> links = new ArrayList<>();

    public Skill() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<LearningPath> getLearningPaths() { return learningPaths; }
    public void setLearningPaths(List<LearningPath> learningPaths) { this.learningPaths = learningPaths; }

    public List<String> getLinks() { return links; }
    public void setLinks(List<String> links) { this.links = links; }
}