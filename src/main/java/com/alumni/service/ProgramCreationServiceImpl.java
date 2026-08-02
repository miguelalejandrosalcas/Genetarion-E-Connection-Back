package com.alumni.service;

import com.alumni.dto.LearningPathCreationDTO;
import com.alumni.dto.LearningPathDTO;
import com.alumni.dto.ProgramCreationRequestDTO;
import com.alumni.dto.ProgramCreationResponseDTO;
import com.alumni.dto.ProgramDTO;
import com.alumni.model.LearningPath;
import com.alumni.model.Program;
import com.alumni.model.Skill;
import com.alumni.repository.LearningPathRepository;
import com.alumni.repository.ProgramRepository;
import com.alumni.repository.SkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramCreationServiceImpl implements ProgramCreationService {

    private final ProgramRepository programRepository;
    private final LearningPathRepository learningPathRepository;
    private final SkillRepository skillRepository;

    public ProgramCreationServiceImpl(ProgramRepository programRepository,
                                      LearningPathRepository learningPathRepository,
                                      SkillRepository skillRepository) {
        this.programRepository = programRepository;
        this.learningPathRepository = learningPathRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    @Transactional
    public ProgramCreationResponseDTO createFull(ProgramCreationRequestDTO programRequest) {
        if (programRequest.getName() == null || programRequest.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del programa es obligatorio");
        }
        if (programRequest.getRoutes() == null || programRequest.getRoutes().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El programa necesita al menos una ruta");
        }

        Program program = new Program();
        program.setName(programRequest.getName());
        program.setDescription(programRequest.getDescription());
        program = programRepository.save(program);

        List<LearningPath> savedPaths = new ArrayList<>();
        for (LearningPathCreationDTO route : programRequest.getRoutes()) {
            if (route.getTitle() == null || route.getTitle().isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cada ruta necesita un título");
            }
            if (route.getTopics() == null || route.getTopics().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La ruta \"" + route.getTitle() + "\" necesita al menos un topic");
            }

            LearningPath learningPath = new LearningPath();
            learningPath.setName(route.getTitle());
            learningPath.setProgram(program);
            learningPath.setActive(true);
            learningPath.setSkills(resolveSkills(route.getTopics()));

            savedPaths.add(learningPathRepository.save(learningPath));
        }

        List<LearningPathDTO> pathDTOs = savedPaths.stream()
                .map(LearningPathDTO::fromEntity)
                .collect(Collectors.toList());

        return new ProgramCreationResponseDTO(ProgramDTO.fromEntity(program), pathDTOs);
    }

    private List<Skill> resolveSkills(List<String> topics) {
        List<Skill> skills = new ArrayList<>();
        for (String topic : topics) {
            String trimmed = topic.trim();
            Skill skill = skillRepository.findBySkillNameIgnoreCase(trimmed)
                    .orElseGet(() -> {
                        Skill newSkill = new Skill();
                        newSkill.setSkillName(trimmed);
                        return skillRepository.save(newSkill);
                    });
            skills.add(skill);
        }
        return skills;
    }
}