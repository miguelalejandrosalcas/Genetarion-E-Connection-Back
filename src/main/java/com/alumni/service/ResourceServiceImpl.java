package com.alumni.service;

import com.alumni.dto.ResourceDTO;
import com.alumni.enums.Section;
import com.alumni.model.Administrator;
import com.alumni.model.Category;
import com.alumni.model.Resource;
import com.alumni.model.ResourceType;
import com.alumni.repository.AdministratorRepository;
import com.alumni.repository.CategoryRepository;
import com.alumni.repository.ResourceRepository;
import com.alumni.repository.ResourceTypeRepository;
import com.alumni.repository.InteractionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final CategoryRepository categoryRepository;
    private final ResourceTypeRepository resourceTypeRepository;
    private final AdministratorRepository administratorRepository;
    private final InteractionRepository interactionRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository,
                               CategoryRepository categoryRepository,
                               ResourceTypeRepository resourceTypeRepository,
                               AdministratorRepository administratorRepository,InteractionRepository interactionRepository) {
        this.resourceRepository = resourceRepository;
        this.categoryRepository = categoryRepository;
        this.resourceTypeRepository = resourceTypeRepository;
        this.administratorRepository = administratorRepository;
        this.interactionRepository = interactionRepository;
    }

    @Override
    public List<ResourceDTO> getAll() {
        return toDtoList(resourceRepository.findAll());
    }

    @Override
    public List<ResourceDTO> getAllActive() {
        return toDtoList(resourceRepository.findByActiveTrue());
    }

    @Override
    public ResourceDTO getById(long id) {
        return ResourceDTO.fromEntity(findEntityOrThrow(id));
    }

    @Override
    public List<ResourceDTO> getBySection(String sectionParam) {
        Section section = parseSection(sectionParam);
        return toDtoList(resourceRepository.findBySectionAndActiveTrue(section));
    }

    @Override
    public List<ResourceDTO> getByCategory(Long categoryId) {
        return toDtoList(resourceRepository.findByCategory_IdAndActiveTrue(categoryId));
    }

    @Override
    public List<ResourceDTO> getFeatured() {
        List<Long> topIds = interactionRepository.findTopResourceIdsByInteractions();
        return topIds.stream()
                .limit(3)
                .map(this::findEntityOrThrow)
                .map(ResourceDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> search(String query) {
        return toDtoList(resourceRepository.findByTitleContainingIgnoreCaseAndActiveTrue(query));
    }

    @Override
    public ResourceDTO create(ResourceDTO dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El título es obligatorio");
        }
        if (dto.getUrl() == null || dto.getUrl().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La URL es obligatoria");
        }
        if (dto.getSection() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La sección es obligatoria (library o recording)");
        }

        Resource resource = new Resource();
        resource.setTitle(dto.getTitle());
        resource.setDescription(dto.getDescription());
        resource.setSection(dto.getSection());
        resource.setUrl(dto.getUrl());
        resource.setDurationMinutes(dto.getDurationMinutes());
        resource.setPublicationDate(dto.getPublicationDate() != null ? dto.getPublicationDate() : LocalDate.now());
        resource.setFeatured(dto.isFeatured());
        resource.setActive(true);
        resource.setViews(0);
        resource.setDownloads(0);
        resource.setThumbnailUrl(dto.getThumbnailUrl());
        resource.setFileName(dto.getFileName());
        resource.setFileSize(dto.getFileSize());
        resource.setCreatedAt(LocalDateTime.now());
        resource.setUpdatedAt(LocalDateTime.now());

        applyRelations(resource, dto);

        return ResourceDTO.fromEntity(resourceRepository.save(resource));
    }

    @Override
    public ResourceDTO update(long id, ResourceDTO dto) {
        Resource resource = findEntityOrThrow(id);

        if (dto.getTitle() != null) resource.setTitle(dto.getTitle());
        if (dto.getDescription() != null) resource.setDescription(dto.getDescription());
        if (dto.getSection() != null) resource.setSection(dto.getSection());
        if (dto.getUrl() != null) resource.setUrl(dto.getUrl());
        if (dto.getPublicationDate() != null) resource.setPublicationDate(dto.getPublicationDate());
        resource.setDurationMinutes(dto.getDurationMinutes());
        resource.setFeatured(dto.isFeatured());
        resource.setActive(dto.isActive());
        if (dto.getThumbnailUrl() != null) resource.setThumbnailUrl(dto.getThumbnailUrl());
        if (dto.getFileName() != null) resource.setFileName(dto.getFileName());
        if (dto.getFileSize() != null) resource.setFileSize(dto.getFileSize());

        applyRelations(resource, dto);

        resource.setUpdatedAt(LocalDateTime.now());

        return ResourceDTO.fromEntity(resourceRepository.save(resource));
    }

    @Override
    public void delete(long id) {
        resourceRepository.delete(findEntityOrThrow(id));
    }

    @Override
    public ResourceDTO registerView(long id) {
        Resource resource = findEntityOrThrow(id);
        resource.setViews(resource.getViews() + 1);
        return ResourceDTO.fromEntity(resourceRepository.save(resource));
    }

    @Override
    public ResourceDTO registerDownload(long id) {
        Resource resource = findEntityOrThrow(id);
        resource.setDownloads(resource.getDownloads() + 1);
        return ResourceDTO.fromEntity(resourceRepository.save(resource));
    }

    private void applyRelations(Resource resource, ResourceDTO dto) {
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "No existe una categoría con id " + dto.getCategoryId()));
            resource.setCategory(category);
        }
        if (dto.getResourceTypeId() != null) {
            ResourceType resourceType = resourceTypeRepository.findById(dto.getResourceTypeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "No existe un tipo de recurso con id " + dto.getResourceTypeId()));
            resource.setResourceType(resourceType);
        }
        if (dto.getAdministratorId() != null) {
            Administrator administrator = administratorRepository.findById(dto.getAdministratorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "No existe un administrador con id " + dto.getAdministratorId()));
            resource.setAdministrator(administrator);
        }
    }

    private Resource findEntityOrThrow(long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe un recurso con id " + id));
    }

    private Section parseSection(String sectionParam) {
        try {
            return Section.valueOf(sectionParam.toLowerCase());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Sección inválida: '" + sectionParam + "' (usa 'library' o 'recording')");
        }
    }

    private List<ResourceDTO> toDtoList(List<Resource> resources) {
        return resources.stream()
                .map(ResourceDTO::fromEntity)
                .collect(Collectors.toList());
    }
}