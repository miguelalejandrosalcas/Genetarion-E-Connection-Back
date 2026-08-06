package com.alumni.service;

import com.alumni.dto.AuthResponseDTO;
import com.alumni.dto.LoginRequestDTO;
import com.alumni.dto.LoginResponseDTO;
import com.alumni.dto.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO request);
}