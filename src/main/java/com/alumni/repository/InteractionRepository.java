package com.alumni.repository;

import com.alumni.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    List<Interaction> findByResource_IdOrderByCreatedAtDesc(Long resourceId);

    @Query("SELECT i.resource.id FROM Interaction i " +
            "WHERE i.resource.active = true AND i.resource.section = com.alumni.enums.Section.library " +
            "GROUP BY i.resource.id " +
            "ORDER BY COUNT(i.id) DESC")
    List<Long> findTopResourceIdsByInteractions();
}