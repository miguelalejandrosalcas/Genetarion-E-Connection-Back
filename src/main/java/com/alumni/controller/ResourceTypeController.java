package com.alumni.controller;

import com.alumni.dto.ResourceTypeDTO;
import com.alumni.service.ResourceTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resource-types")
public class ResourceTypeController {

    private final ResourceTypeService resourceTypeService;

    public ResourceTypeController(ResourceTypeService resourceTypeService) {
        this.resourceTypeService = resourceTypeService;
    }

    @GetMapping
    public List<ResourceTypeDTO> getResourceTypes() {
        return resourceTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ResourceTypeDTO getResourceType(@PathVariable long id) {
        return resourceTypeService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceTypeDTO createResourceType(@RequestBody ResourceTypeDTO dto) {
        return resourceTypeService.create(dto);
    }

    @PutMapping("/{id}")
    public ResourceTypeDTO updateResourceType(@PathVariable long id, @RequestBody ResourceTypeDTO dto) {
        return resourceTypeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResourceType(@PathVariable long id) {
        resourceTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}