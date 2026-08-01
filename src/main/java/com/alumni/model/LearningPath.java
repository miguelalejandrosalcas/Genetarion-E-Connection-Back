package com.alumni.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "learning_paths")
public class LearningPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "learningPath", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PathSkill> pathSkills = new ArrayList<>();


    public LearningPath() {

    }

    public LearningPath(String name, String description, boolean active, List<PathSkill> pathSkills) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.pathSkills = pathSkills;
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

    public List<PathSkill> getPathSkills() {
        return pathSkills;
    }

    public void setPathSkills(List<PathSkill> pathSkills) {
        this.pathSkills = pathSkills;
    }


}
