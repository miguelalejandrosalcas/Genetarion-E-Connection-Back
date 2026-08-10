package com.alumni.service;

import com.alumni.dto.MentorProfileRequestDTO;
import com.alumni.dto.ProfileDTO;
import com.alumni.dto.ProfileUpdateRequestDTO;
import com.alumni.model.Administrator;
import com.alumni.model.MentorProfile;
import com.alumni.repository.AdministratorRepository;
import com.alumni.repository.MentorProfileRepository;
import com.alumni.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final AdministratorRepository administratorRepository;
    private final MentorProfileRepository mentorProfileRepository;
    private final JwtService jwtService;

    public ProfileServiceImpl(AdministratorRepository administratorRepository,
                              MentorProfileRepository mentorProfileRepository,
                              JwtService jwtService) {
        this.administratorRepository = administratorRepository;
        this.mentorProfileRepository = mentorProfileRepository;
        this.jwtService = jwtService;
    }

    @Override
    public ProfileDTO getMyProfile(String currentEmail) {
        Administrator administrator = administratorRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sesión inválida."));

        MentorProfile profile = mentorProfileRepository
                .findByAdministratorId(administrator.getId())
                .orElse(null);

        return ProfileDTO.fromEntities(administrator, profile, null);
    }

    @Override
    @Transactional
    public ProfileDTO updateMyProfile(String currentEmail, ProfileUpdateRequestDTO request) {
        Administrator administrator = administratorRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sesión inválida."));

        boolean emailChanged = !administrator.getEmail().equalsIgnoreCase(request.getEmail());
        if (emailChanged && administratorRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ese correo ya está en uso por otra cuenta.");
        }

        administrator.setName(request.getName());
        administrator.setEmail(request.getEmail());
        administratorRepository.save(administrator);

        MentorProfileRequestDTO profileRequest = request.getProfile();

        MentorProfile profile = mentorProfileRepository
                .findByAdministratorId(administrator.getId())
                .orElseGet(() -> new MentorProfile(administrator, null, null, null, null, null, null, null));

        profile.setAdministrator(administrator);
        if (profileRequest != null) {
            profile.setProfileImageUrl(profileRequest.getProfileImageUrl());
            profile.setLinkedin(profileRequest.getLinkedin());
            profile.setAbout(profileRequest.getAbout());
            profile.setGenerationProgram(profileRequest.getGenerationProgram());
            profile.setMentorAreas(profileRequest.getMentorAreas());
            profile.setSkills(profileRequest.getSkills());
            profile.setMentorType(profileRequest.getMentorType());
        }
        mentorProfileRepository.save(profile);

        String newToken = emailChanged
                ? jwtService.generateToken(administrator.getEmail(), administrator.getRole())
                : null;

        return ProfileDTO.fromEntities(administrator, profile, newToken);
    }
}