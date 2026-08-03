package com.alumni.service;

import com.alumni.dto.DashboardStatsDTO;
import com.alumni.enums.Section;
import com.alumni.repository.CategoryRepository;
import com.alumni.repository.ProgramRepository;
import com.alumni.repository.ResourceRepository;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    private final ResourceRepository resourceRepository;
    private final CategoryRepository categoryRepository;
    private final ProgramRepository programRepository;

    public StatsServiceImpl(ResourceRepository resourceRepository,
                            CategoryRepository categoryRepository,
                            ProgramRepository programRepository) {
        this.resourceRepository = resourceRepository;
        this.categoryRepository = categoryRepository;
        this.programRepository = programRepository;
    }

    @Override
    public DashboardStatsDTO getDashboardStats() {
        long resources = resourceRepository.countByActiveTrue();
        long categories = categoryRepository.countByActiveTrue();
        long recordings = resourceRepository.countBySectionAndActiveTrue(Section.recording);
        long programs = programRepository.count();

        return new DashboardStatsDTO(resources, categories, recordings, programs);
    }
}

