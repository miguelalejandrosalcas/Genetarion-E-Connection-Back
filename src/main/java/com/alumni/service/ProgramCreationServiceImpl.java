package com.alumni.service;

import com.alumni.dto.LearningPathCreationDTO;
import com.alumni.dto.LearningPathDTO;
import com.alumni.dto.ProgramCreationRequestDTO;
import com.alumni.dto.ProgramCreationResponseDTO;
import com.alumni.dto.ProgramDTO;
import com.alumni.model.LearningPath;
import com.alumni.model.Program;
import com.alumni.model.Skill;
import com.alumni.dto.LearningPathWithSkillsDTO;
import com.alumni.dto.ProgramFullDTO;
import com.alumni.repository.LearningPathRepository;
import com.alumni.repository.ProgramRepository;
import com.alumni.repository.SkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public ProgramCreationResponseDTO createFull(ProgramCreationRequestDTO request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del programa es obligatorio");
        }
        if (request.getRoutes() == null || request.getRoutes().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El programa necesita al menos una ruta");
        }

        Program program = new Program();
        program.setName(request.getName());
        program.setDescription(request.getDescription());
        program = programRepository.save(program);

        List<LearningPath> savedPaths = new ArrayList<>();
        for (LearningPathCreationDTO route : request.getRoutes()) {
            validateRoute(route);

            LearningPath learningPath = new LearningPath();
            learningPath.setName(route.getTitle());
            learningPath.setProgram(program);
            learningPath.setActive(true);
            learningPath.setSkills(resolveSkills(route.getTopics()));

            savedPaths.add(learningPathRepository.save(learningPath));
        }

        return buildResponse(program, savedPaths);
    }

    @Override
    @Transactional
    public ProgramCreationResponseDTO updateFull(Long programId, ProgramCreationRequestDTO request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del programa es obligatorio");
        }
        if (request.getRoutes() == null || request.getRoutes().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El programa necesita al menos una ruta");
        }

        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe un programa con id " + programId));

        program.setName(request.getName());
        program.setDescription(request.getDescription());
        program = programRepository.save(program);

        Map<Long, LearningPath> existingById = program.getLearningPaths().stream()
                .collect(Collectors.toMap(LearningPath::getId, lp -> lp));

        Set<Long> keptIds = new HashSet<>();
        List<LearningPath> savedPaths = new ArrayList<>();

        for (LearningPathCreationDTO route : request.getRoutes()) {
            validateRoute(route);

            LearningPath learningPath;
            if (route.getId() != null) {
                learningPath = existingById.get(route.getId());
                if (learningPath == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "La ruta con id " + route.getId() + " no pertenece a este programa");
                }
            } else {
                learningPath = new LearningPath();
                learningPath.setProgram(program);
                learningPath.setActive(true);
            }

            learningPath.setName(route.getTitle());
            learningPath.setSkills(resolveSkills(route.getTopics()));

            LearningPath saved = learningPathRepository.save(learningPath);
            savedPaths.add(saved);
            keptIds.add(saved.getId());
        }

        for (LearningPath existing : existingById.values()) {
            if (!keptIds.contains(existing.getId())) {
                learningPathRepository.delete(existing);
            }
        }

        return buildResponse(program, savedPaths);
    }

    private void validateRoute(LearningPathCreationDTO route) {
        if (route.getTitle() == null || route.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cada ruta necesita un título");
        }
        if (route.getTopics() == null || route.getTopics().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La ruta \"" + route.getTitle() + "\" necesita al menos un topic");
        }
    }

    private ProgramCreationResponseDTO buildResponse(Program program, List<LearningPath> paths) {
        List<LearningPathDTO> pathDTOs = paths.stream()
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

    @Override
    @Transactional(readOnly = true)
    public ProgramFullDTO getFull(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe un programa con id " + programId));

        List<LearningPathWithSkillsDTO> paths = program.getLearningPaths().stream()
                .map(LearningPathWithSkillsDTO::fromEntity)
                .collect(Collectors.toList());

        return new ProgramFullDTO(ProgramDTO.fromEntity(program), paths);
    }
}