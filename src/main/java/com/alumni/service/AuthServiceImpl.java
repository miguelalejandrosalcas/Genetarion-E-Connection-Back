package com.alumni.service;

import com.alumni.dto.AuthResponseDTO;
import com.alumni.dto.LoginRequestDTO;
import com.alumni.dto.LoginResponseDTO;
import com.alumni.dto.RegisterRequestDTO;
import com.alumni.model.Administrator;
import com.alumni.repository.AdministratorRepository;
import com.alumni.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(AdministratorRepository administratorRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        Administrator administrator = administratorRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas."));

        if (!passwordEncoder.matches(request.getPassword(), administrator.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas.");
        }

        String token = jwtService.generateToken(administrator.getEmail(), administrator.getRole());

        return new LoginResponseDTO(token, administrator.getName(), administrator.getEmail(), administrator.getRole());
    }
}