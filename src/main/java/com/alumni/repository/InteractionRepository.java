package com.alumni.repository;

import com.alumni.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    List<Interaction> findByResource_IdOrderByCreatedAtDesc(Long resourceId);
}