package com.alumni.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "path_skills")
public class PathSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "resource_id", nullable = false)
    private Resource resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "path_id", nullable = false)
    private LearningPath learningPath;

    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @Column(columnDefinition = "TEXT")
    private String description;

    public PathSkill() {

    }

    public PathSkill(Resource resource, LearningPath learningPath, String skillName, String description) {
        this.resource = resource;
        this.learningPath = learningPath;
        this.skillName = skillName;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public LearningPath getLearningPath() {
        return learningPath;
    }

    public void setLearningPath(LearningPath learningPath) {
        this.learningPath = learningPath;
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
}
