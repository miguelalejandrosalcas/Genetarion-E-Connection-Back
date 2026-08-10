package com.alumni.service;

import com.alumni.dto.DashboardStatsDTO;
import com.alumni.enums.Section;
import com.alumni.repository.CategoryRepository;
import com.alumni.repository.ProgramRepository;
import com.alumni.repository.ResourceRepository;
import com.alumni.repository.JobBoardRepository;
import org.springframework.stereotype.Service;


@Service
public class StatsServiceImpl implements StatsService {

    private final ResourceRepository resourceRepository;
    private final CategoryRepository categoryRepository;
    private final ProgramRepository programRepository;
    private final JobBoardRepository jobBoardRepository;

    public StatsServiceImpl(ResourceRepository resourceRepository,
                            CategoryRepository categoryRepository,
                            ProgramRepository programRepository, JobBoardRepository jobBoardRepository) {
        this.resourceRepository = resourceRepository;
        this.categoryRepository = categoryRepository;
        this.programRepository = programRepository;
        this.jobBoardRepository = jobBoardRepository;
    }

    @Override
    public DashboardStatsDTO getDashboardStats() {
        long resources = resourceRepository.countByActiveTrue();
        long categories = categoryRepository.countByActiveTrue();
        long recordings = resourceRepository.countBySectionAndActiveTrue(Section.recording);
        long programs = programRepository.count();
        long libraryResources = resourceRepository.countBySectionAndActiveTrue(Section.library);
        long jobBoards = jobBoardRepository.countByActiveTrue();

        return new DashboardStatsDTO(resources, categories, recordings, programs, libraryResources, jobBoards);
    }
}

