package com.alumni.service;

import com.alumni.dto.ProgramDTO;

import java.util.List;

public interface ProgramService {
    List<ProgramDTO> getAll();
    ProgramDTO getById(Long id);
    ProgramDTO create(ProgramDTO dto);
    ProgramDTO update(Long id, ProgramDTO dto);
    void delete(Long id);
}