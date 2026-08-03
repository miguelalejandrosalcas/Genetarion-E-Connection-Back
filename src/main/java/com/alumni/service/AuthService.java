package com.alumni.service;

import com.alumni.dto.AuthResponseDTO;
import com.alumni.dto.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO request);
}