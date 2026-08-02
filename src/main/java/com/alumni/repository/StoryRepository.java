package com.alumni.repository;

import com.alumni.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findByActiveTrue();
    List<Story> findByFeaturedTrueAndActiveTrue();
}