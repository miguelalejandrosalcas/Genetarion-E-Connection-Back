package com.alumni.repository;

import com.alumni.enums.Section;
import com.alumni.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findByActiveTrue();

    List<Resource> findBySectionAndActiveTrue(Section section);

    List<Resource> findByCategory_IdAndActiveTrue(Long categoryId);

    List<Resource> findByFeaturedTrueAndActiveTrue();

    List<Resource> findByTitleContainingIgnoreCaseAndActiveTrue(String title);
}