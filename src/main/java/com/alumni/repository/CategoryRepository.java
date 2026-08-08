package com.alumni.repository;

import com.alumni.enums.Section;
import com.alumni.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByActiveTrue();

    long countByActiveTrue();

    List<Category> findBySectionAndActiveTrue(Section section);
}