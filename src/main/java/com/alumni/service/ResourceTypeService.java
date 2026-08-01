package com.alumni.service;

import com.alumni.dto.ResourceTypeDTO;

import java.util.List;

public interface ResourceTypeService {
    List<ResourceTypeDTO> getAll();
    ResourceTypeDTO getById(long id);
    ResourceTypeDTO create(ResourceTypeDTO dto);
    ResourceTypeDTO update(long id, ResourceTypeDTO dto);
    void delete(long id);
}