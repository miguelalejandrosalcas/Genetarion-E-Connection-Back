package com.alumni.service;

import com.alumni.dto.LearningPathDTO;

import java.util.List;

public interface LearningPathService {
    List<LearningPathDTO> getAll();
    List<LearningPathDTO> getAllActive();
    LearningPathDTO getById(Long id);
    LearningPathDTO create(LearningPathDTO dto);
    LearningPathDTO update(Long id, LearningPathDTO dto);
    void delete(Long id);
}