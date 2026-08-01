package com.alumni.controller;

import com.alumni.dto.ResourceDTO;
import com.alumni.service.ResourcesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourcesService resourcesService;

    public ResourceController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @GetMapping
    public List<ResourceDTO> getResources(
            @RequestParam(required = false) String section,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Boolean featured,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "false") boolean includeInactive) {

        // valdiar luego si esta lógica deberia ir en el servicio
        if (search != null && !search.isBlank()) {
            return resourcesService.search(search);
        }
        if (section != null && !section.isBlank()) {
            return resourcesService.getBySection(section);
        }
        if (categoryId != null) {
            return resourcesService.getByCategory(categoryId);
        }
        if (Boolean.TRUE.equals(featured)) {
            return resourcesService.getFeatured();
        }
        return includeInactive ? resourcesService.getAll() : resourcesService.getAllActive();
    }

    @GetMapping("/{id}")
    public ResourceDTO getResource(@PathVariable long id) {
        return resourcesService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceDTO createResource(@RequestBody ResourceDTO dto) {
        return resourcesService.create(dto);
    }

    @PutMapping("/{id}")
    public ResourceDTO updateResource(@PathVariable long id, @RequestBody ResourceDTO dto) {
        return resourcesService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable long id) {
        resourcesService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/view")
    public ResourceDTO registerView(@PathVariable long id) {
        return resourcesService.registerView(id);
    }

    @PatchMapping("/{id}/download")
    public ResourceDTO registerDownload(@PathVariable long id) {
        return resourcesService.registerDownload(id);
    }
}