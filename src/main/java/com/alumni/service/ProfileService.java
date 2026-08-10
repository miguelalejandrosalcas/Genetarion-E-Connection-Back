package com.alumni.service;

import com.alumni.dto.ProfileDTO;
import com.alumni.dto.ProfileUpdateRequestDTO;

public interface ProfileService {
    ProfileDTO getMyProfile(String currentEmail);
    ProfileDTO updateMyProfile(String currentEmail, ProfileUpdateRequestDTO request);
}