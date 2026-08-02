package com.alumni.controller;

import com.alumni.dto.SkillDTO;
import com.alumni.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<SkillDTO> getSkills() {
        return skillService.getAll();
    }

    @GetMapping("/{id}")
    public SkillDTO getSkill(@PathVariable Long id) {
        return skillService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillDTO createSkill(@RequestBody SkillDTO dto) {
        return skillService.create(dto);
    }

    @PutMapping("/{id}")
    public SkillDTO updateSkill(@PathVariable Long id, @RequestBody SkillDTO dto) {
        return skillService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.delete(id);
        return ResponseEntity.noContent().build();
    }
}