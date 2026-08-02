package com.alumni.dto;

import java.util.List;

public class ProgramCreationResponseDTO {
    private ProgramDTO program;
    private List<LearningPathDTO> learningPaths;

    public ProgramCreationResponseDTO() {
    }

    public ProgramCreationResponseDTO(ProgramDTO program, List<LearningPathDTO> learningPaths) {
        this.program = program;
        this.learningPaths = learningPaths;
    }

    public ProgramDTO getProgram() {
        return program;
    }

    public void setProgram(ProgramDTO program) {
        this.program = program;
    }

    public List<LearningPathDTO> getLearningPaths() {
        return learningPaths;
    }

    public void setLearningPaths(List<LearningPathDTO> learningPaths) {
        this.learningPaths = learningPaths;
    }
}