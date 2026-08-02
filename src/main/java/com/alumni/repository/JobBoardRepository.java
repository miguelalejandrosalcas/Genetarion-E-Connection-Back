package com.alumni.repository;

import com.alumni.model.JobBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobBoardRepository extends JpaRepository<JobBoard, Long> {
    List<JobBoard> findByActiveTrue();
}