package com.alumni.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<LearningPath> learningPaths = new ArrayList<>();

    public Program() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<LearningPath> getLearningPaths() { return learningPaths; }
    public void setLearningPaths(List<LearningPath> learningPaths) { this.learningPaths = learningPaths; }
}