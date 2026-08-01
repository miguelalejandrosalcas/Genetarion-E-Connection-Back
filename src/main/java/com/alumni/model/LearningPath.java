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

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Esta es la clave: el campo debe llamarse "program"
    // porque Program.learningPaths usa mappedBy = "program"
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_programs", nullable = false)
    private Program program;

    @ManyToMany
    @JoinTable(
            name = "learning_path_skills",
            joinColumns = @JoinColumn(name = "id_learning_paths"),
            inverseJoinColumns = @JoinColumn(name = "id_skills")
    )
    private List<Skill> skills = new ArrayList<>();

    @Column(name = "active", nullable = false)
    private boolean active = true;

    public LearningPath() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Program getProgram() { return program; }
    public void setProgram(Program program) { this.program = program; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public List<Skill> getSkills() { return skills; }
    public void setSkills(List<Skill> skills) { this.skills = skills; }
}