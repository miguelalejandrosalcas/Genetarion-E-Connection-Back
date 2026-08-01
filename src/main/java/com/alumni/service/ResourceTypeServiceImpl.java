package com.alumni.service;

import com.alumni.dto.ResourceTypeDTO;
import com.alumni.model.ResourceType;
import com.alumni.repository.ResourceTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {

    private final ResourceTypeRepository resourceTypeRepository;

    public ResourceTypeServiceImpl(ResourceTypeRepository resourceTypeRepository) {
        this.resourceTypeRepository = resourceTypeRepository;
    }

    @Override
    public List<ResourceTypeDTO> getAll() {
        return resourceTypeRepository.findAll().stream()
                .map(ResourceTypeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ResourceTypeDTO getById(long id) {
        return ResourceTypeDTO.fromEntity(findEntityOrThrow(id));
    }

    @Override
    public ResourceTypeDTO create(ResourceTypeDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
        ResourceType resourceType = new ResourceType();
        resourceType.setName(dto.getName());
        return ResourceTypeDTO.fromEntity(resourceTypeRepository.save(resourceType));
    }

    @Override
    public ResourceTypeDTO update(long id, ResourceTypeDTO dto) {
        ResourceType resourceType = findEntityOrThrow(id);
        if (dto.getName() != null) resourceType.setName(dto.getName());
        return ResourceTypeDTO.fromEntity(resourceTypeRepository.save(resourceType));
    }

    @Override
    public void delete(long id) {
        resourceTypeRepository.delete(findEntityOrThrow(id));
    }

    private ResourceType findEntityOrThrow(long id) {
        return resourceTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe un tipo de recurso con id " + id));
    }
}