package com.alumni.controller;

import com.alumni.dto.CreateResourceRequest;
import com.alumni.dto.ResourceDTO;
import com.alumni.model.Resource;
import com.alumni.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public List<ResourceDTO> getResources(
            @RequestParam(required = false) String section,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Boolean featured,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "false") boolean includeInactive) {

        if (search != null && !search.isBlank()) {
            return resourceService.search(search);
        }
        if (section != null && !section.isBlank()) {
            return resourceService.getBySection(section);
        }
        if (categoryId != null) {
            return resourceService.getByCategory(categoryId);
        }
        if (Boolean.TRUE.equals(featured)) {
            return resourceService.getFeatured();
        }
        return includeInactive ? resourceService.getAll() : resourceService.getAllActive();
    }

    @GetMapping("/{id}")
    public ResourceDTO getResource(@PathVariable long id) {
        return resourceService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceDTO createResource(@RequestBody ResourceDTO dto) {
        return resourceService.create(dto);
    }

    @PutMapping("/{id}")
    public ResourceDTO updateResource(@PathVariable long id, @RequestBody ResourceDTO dto) {
        return resourceService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable long id) {
        resourceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/view")
    public ResourceDTO registerView(@PathVariable long id) {
        return resourceService.registerView(id);
    }

    @PatchMapping("/{id}/download")
    public ResourceDTO registerDownload(@PathVariable long id) {
        return resourceService.registerDownload(id);
    }

/*    @PostMapping
    public ResponseEntity<Resource> createResource(
            @RequestBody CreateResourceRequest request,
            @PathVariable Long id) {

        Resource resource = resourceService.syncResource(
                id,
                request.getCategoryId(),
                request.getResourceTypeId(),
                request.getTitle(),
                request.getDescription(),
                request.getSection(),
                request.getUrl(),
                request.getDurationMinutes(),
                request.getPublicationDate(),
                request.isFeatured()
        );

        return ResponseEntity.ok(resource);
    }*/
}