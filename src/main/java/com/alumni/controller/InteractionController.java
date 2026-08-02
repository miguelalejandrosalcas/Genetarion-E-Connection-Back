package com.alumni.controller;

import com.alumni.dto.InteractionDTO;
import com.alumni.service.InteractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @GetMapping
    public List<InteractionDTO> getInteractions(@RequestParam(required = false) Long resourceId) {
        return resourceId != null ? interactionService.getByResource(resourceId) : interactionService.getAll();
    }

    @GetMapping("/{id}")
    public InteractionDTO getInteraction(@PathVariable Long id) {
        return interactionService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InteractionDTO createInteraction(@RequestBody InteractionDTO dto) {
        return interactionService.create(dto);
    }

    @PutMapping("/{id}")
    public InteractionDTO updateInteraction(@PathVariable Long id, @RequestBody InteractionDTO dto) {
        return interactionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInteraction(@PathVariable Long id) {
        interactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}