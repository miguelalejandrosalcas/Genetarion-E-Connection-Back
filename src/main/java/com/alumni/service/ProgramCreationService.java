package com.alumni.service;

import com.alumni.dto.ProgramCreationRequestDTO;
import com.alumni.dto.ProgramCreationResponseDTO;
import com.alumni.dto.ProgramFullDTO;

public interface ProgramCreationService {
    ProgramCreationResponseDTO createFull(ProgramCreationRequestDTO request);
    ProgramCreationResponseDTO updateFull(Long programId, ProgramCreationRequestDTO request);
    ProgramFullDTO getFull(Long programId);
}