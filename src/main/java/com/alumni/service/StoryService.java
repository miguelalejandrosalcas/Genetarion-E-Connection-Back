package com.alumni.service;

import com.alumni.dto.StoryDTO;

import java.util.List;

public interface StoryService {
    List<StoryDTO> getAll();
    List<StoryDTO> getAllActive();
    List<StoryDTO> getFeatured();
    StoryDTO getById(Long id);
    StoryDTO create(StoryDTO dto);
    StoryDTO update(Long id, StoryDTO dto);
    void delete(Long id);
}