package com.alumni.service;

import com.alumni.dto.ResourceDTO;
import com.alumni.enums.Section;
import com.alumni.model.Resource;

import java.time.LocalDate;
import java.util.List;

public interface ResourceService {
    List<ResourceDTO> getAll();
    List<ResourceDTO> getAllActive();
    ResourceDTO getById(long id);
    List<ResourceDTO> getBySection(String sectionParam);
    List<ResourceDTO> getByCategory(Long categoryId);
    List<ResourceDTO> getFeatured();
    List<ResourceDTO> search(String query);
    ResourceDTO create(ResourceDTO dto);
    ResourceDTO update(long id, ResourceDTO dto);
    void delete(long id);
    ResourceDTO registerView(long id);
    ResourceDTO registerDownload(long id);
    Resource syncResource(
            Long administratorId,
            Long categoryId,
            Long resourceTypeId,
            String title,
            String description,
            Section section,
            String url,
            int durationMinutes,
            LocalDate publicationDate,
            boolean featured);
}