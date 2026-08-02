package com.alumni.service;

import com.alumni.dto.SkillDTO;
import com.alumni.model.Skill;
import com.alumni.repository.SkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<SkillDTO> getAll() {
        return skillRepository.findAll().stream()
                .map(SkillDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SkillDTO getById(Long id) {
        return SkillDTO.fromEntity(findEntityOrThrow(id));
    }

    @Override
    public SkillDTO create(SkillDTO dto) {
        if (dto.getSkillName() == null || dto.getSkillName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la skill es obligatorio");
        }
        Skill skill = new Skill();
        skill.setSkillName(dto.getSkillName());
        skill.setDescription(dto.getDescription());
        return SkillDTO.fromEntity(skillRepository.save(skill));
    }

    @Override
    public SkillDTO update(Long id, SkillDTO dto) {
        Skill skill = findEntityOrThrow(id);
        if (dto.getSkillName() != null) skill.setSkillName(dto.getSkillName());
        if (dto.getDescription() != null) skill.setDescription(dto.getDescription());
        return SkillDTO.fromEntity(skillRepository.save(skill));
    }

    @Override
    public void delete(Long id) {
        Skill skill = findEntityOrThrow(id);
        if (!skill.getLearningPaths().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No se puede borrar: la skill está en uso por " + skill.getLearningPaths().size()
                            + " ruta(s) de aprendizaje");
        }
        skillRepository.delete(skill);
    }

    private Skill findEntityOrThrow(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe una skill con id " + id));
    }
}