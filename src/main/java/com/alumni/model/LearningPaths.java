package com.alumni.model;

import jakarta.persistence.*;
import jdk.jfr.Description;
import jdk.jfr.Name;

@Entity
@Table(name = "learning_paths")
public class LearningPaths {
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private Description description;

    @Column(name = "active", nullable = false)
    private int active;


    public LearningPaths() {

    }

    public LearningPaths(String name, Description description, int active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        description = description;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        active = active;
    }
}