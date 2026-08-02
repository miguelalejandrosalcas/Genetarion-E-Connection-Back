package com.alumni.controller;

import com.alumni.dto.StoryDTO;
import com.alumni.service.StoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping
    public List<StoryDTO> getStories(
            @RequestParam(required = false, defaultValue = "false") boolean includeInactive,
            @RequestParam(required = false, defaultValue = "false") boolean featured) {
        if (featured) {
            return storyService.getFeatured();
        }
        return includeInactive ? storyService.getAll() : storyService.getAllActive();
    }

    @GetMapping("/{id}")
    public StoryDTO getStory(@PathVariable Long id) {
        return storyService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoryDTO createStory(@RequestBody StoryDTO dto) {
        return storyService.create(dto);
    }

    @PutMapping("/{id}")
    public StoryDTO updateStory(@PathVariable Long id, @RequestBody StoryDTO dto) {
        return storyService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable Long id) {
        storyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}