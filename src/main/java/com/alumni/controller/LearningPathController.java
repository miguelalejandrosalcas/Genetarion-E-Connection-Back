package com.alumni.controller;

import com.alumni.dto.LearningPathDTO;
import com.alumni.service.LearningPathService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learning-paths")
public class LearningPathController {

    private final LearningPathService learningPathService;

    public LearningPathController(LearningPathService learningPathService) {
        this.learningPathService = learningPathService;
    }

    @GetMapping
    public List<LearningPathDTO> getLearningPaths(
            @RequestParam(required = false, defaultValue = "false") boolean includeInactive) {
        return includeInactive ? learningPathService.getAll() : learningPathService.getAllActive();
    }

    @GetMapping("/{id}")
    public LearningPathDTO getLearningPath(@PathVariable Long id) {
        return learningPathService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LearningPathDTO createLearningPath(@RequestBody LearningPathDTO dto) {
        return learningPathService.create(dto);
    }

    @PutMapping("/{id}")
    public LearningPathDTO updateLearningPath(@PathVariable Long id, @RequestBody LearningPathDTO dto) {
        return learningPathService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearningPath(@PathVariable Long id) {
        learningPathService.delete(id);
        return ResponseEntity.noContent().build();
    }
}