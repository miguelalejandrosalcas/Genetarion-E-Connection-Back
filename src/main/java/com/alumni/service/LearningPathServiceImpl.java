package com.alumni.service;

import com.alumni.dto.LearningPathDTO;
import com.alumni.model.LearningPath;
import com.alumni.model.Program;
import com.alumni.model.Skill;
import com.alumni.repository.LearningPathRepository;
import com.alumni.repository.ProgramRepository;
import com.alumni.repository.SkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LearningPathServiceImpl implements LearningPathService {

    private final LearningPathRepository learningPathRepository;
    private final ProgramRepository programRepository;
    private final SkillRepository skillRepository;

    public LearningPathServiceImpl(LearningPathRepository learningPathRepository,
                                   ProgramRepository programRepository,
                                   SkillRepository skillRepository) {
        this.learningPathRepository = learningPathRepository;
        this.programRepository = programRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public List<LearningPathDTO> getAll() {
        return learningPathRepository.findAll().stream()
                .map(LearningPathDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LearningPathDTO> getAllActive() {
        return learningPathRepository.findByActiveTrue().stream()
                .map(LearningPathDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public LearningPathDTO getById(Long id) {
        return LearningPathDTO.fromEntity(findEntityOrThrow(id));
    }

    @Override
    public LearningPathDTO create(LearningPathDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la ruta es obligatorio");
        }
        if (dto.getProgramId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "programId es obligatorio");
        }

        LearningPath learningPath = new LearningPath();
        learningPath.setName(dto.getName());
        learningPath.setDescription(dto.getDescription());
        learningPath.setActive(true);

        applyRelations(learningPath, dto);

        return LearningPathDTO.fromEntity(learningPathRepository.save(learningPath));
    }

    @Override
    public LearningPathDTO update(Long id, LearningPathDTO dto) {
        LearningPath learningPath = findEntityOrThrow(id);

        if (dto.getName() != null) learningPath.setName(dto.getName());
        if (dto.getDescription() != null) learningPath.setDescription(dto.getDescription());
        if (dto.getActive() != null) learningPath.setActive(dto.getActive());

        applyRelations(learningPath, dto);

        return LearningPathDTO.fromEntity(learningPathRepository.save(learningPath));
    }

    @Override
    public void delete(Long id) {
        learningPathRepository.delete(findEntityOrThrow(id));
    }

    private void applyRelations(LearningPath learningPath, LearningPathDTO dto) {
        if (dto.getProgramId() != null) {
            Program program = programRepository.findById(dto.getProgramId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "No existe un programa con id " + dto.getProgramId()));
            learningPath.setProgram(program);
        }

        if (dto.getSkillIds() != null) {
            List<Skill> skills = new ArrayList<>();
            for (Long skillId : dto.getSkillIds()) {
                Skill skill = skillRepository.findById(skillId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "No existe una skill con id " + skillId));
                skills.add(skill);
            }
            learningPath.setSkills(skills);
        }
    }

    private LearningPath findEntityOrThrow(Long id) {
        return learningPathRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe una ruta con id " + id));
    }
}