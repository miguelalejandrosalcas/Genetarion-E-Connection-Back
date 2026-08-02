package com.alumni.service;

import com.alumni.dto.InteractionDTO;

import java.util.List;

public interface InteractionService {
    List<InteractionDTO> getAll();
    List<InteractionDTO> getByResource(Long resourceId);
    InteractionDTO getById(Long id);
    InteractionDTO create(InteractionDTO dto);
    InteractionDTO update(Long id, InteractionDTO dto);
    void delete(Long id);
}