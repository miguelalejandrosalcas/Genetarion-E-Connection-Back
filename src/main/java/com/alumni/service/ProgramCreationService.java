package com.alumni.service;

import com.alumni.dto.ProgramCreationRequestDTO;
import com.alumni.dto.ProgramCreationResponseDTO;

public interface ProgramCreationService {
    ProgramCreationResponseDTO createFull(ProgramCreationRequestDTO request);
}