package com.alumni.service;

import com.alumni.dto.JobBoardDTO;

import java.util.List;

public interface JobBoardService {
    List<JobBoardDTO> getAll();
    List<JobBoardDTO> getAllActive();
    JobBoardDTO getById(Long id);
    JobBoardDTO create(JobBoardDTO dto);
    JobBoardDTO update(Long id, JobBoardDTO dto);
    void delete(Long id);
}