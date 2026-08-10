package com.alumni.controller;

import com.alumni.dto.ProfileDTO;
import com.alumni.dto.ProfileUpdateRequestDTO;
import com.alumni.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me")
    public ProfileDTO getMyProfile(Authentication authentication) {
        return profileService.getMyProfile(authentication.getName());
    }

    @PutMapping("/me")
    public ProfileDTO updateMyProfile(Authentication authentication,
                                      @Valid @RequestBody ProfileUpdateRequestDTO request) {
        return profileService.updateMyProfile(authentication.getName(), request);
    }
}