package com.alumni.dto;

import java.util.List;

public class ProgramFullDTO {
    private ProgramDTO program;
    private List<LearningPathWithSkillsDTO> learningPaths;

    public ProgramFullDTO() {
    }

    public ProgramFullDTO(ProgramDTO program, List<LearningPathWithSkillsDTO> learningPaths) {
        this.program = program;
        this.learningPaths = learningPaths;
    }

    public ProgramDTO getProgram() {
        return program;
    }

    public void setProgram(ProgramDTO program) {
        this.program = program;
    }

    public List<LearningPathWithSkillsDTO> getLearningPaths() {
        return learningPaths;
    }

    public void setLearningPaths(List<LearningPathWithSkillsDTO> learningPaths) {
        this.learningPaths = learningPaths;
    }
}