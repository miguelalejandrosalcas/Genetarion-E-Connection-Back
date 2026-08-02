package com.alumni.service;

import com.alumni.dto.SkillDTO;

import java.util.List;

public interface SkillService {
    List<SkillDTO> getAll();
    SkillDTO getById(Long id);
    SkillDTO create(SkillDTO dto);
    SkillDTO update(Long id, SkillDTO dto);
    void delete(Long id);
}