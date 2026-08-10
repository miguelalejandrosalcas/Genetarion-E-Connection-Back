package com.alumni.repository;

import com.alumni.model.MentorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentorProfileRepository extends JpaRepository<MentorProfile, Long> {
    Optional<MentorProfile> findByAdministratorId(Long administratorId);
}