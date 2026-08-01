package com.alumni.dto;

import com.alumni.model.Program;

public class ProgramDTO {
    private Long id;
    private String name;
    private String description;

    public ProgramDTO() {
    }

    public ProgramDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public static ProgramDTO fromEntity(Program program) {
        return new ProgramDTO(
                program.getId(),
                program.getName(),
                program.getDescription()
        );
    }

    // Program solo tiene constructor vacio, así que se arma con setters
    public Program toEntity() {
        Program program = new Program();
        program.setName(this.name);
        program.setDescription(this.description);
        return program;
    }
}