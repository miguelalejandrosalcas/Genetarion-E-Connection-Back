package com.alumni.service;

import com.alumni.dto.AuthResponseDTO;
import com.alumni.dto.RegisterRequestDTO;
import com.alumni.model.Administrator;
import com.alumni.repository.AdministratorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AdministratorRepository administratorRepository, PasswordEncoder passwordEncoder) {
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (administratorRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una cuenta con ese correo.");
        }

        Administrator administrator = new Administrator(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                "editor",
                true,
                LocalDateTime.now()
        );

        Administrator saved = administratorRepository.save(administrator);

        return new AuthResponseDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getRole());
    }
}