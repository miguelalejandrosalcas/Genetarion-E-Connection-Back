package com.alumni.controller;

import com.alumni.dto.ProgramCreationRequestDTO;
import com.alumni.dto.ProgramCreationResponseDTO;
import com.alumni.dto.ProgramDTO;
import com.alumni.service.ProgramCreationService;
import com.alumni.service.ProgramService;
import com.alumni.dto.ProgramFullDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    private final ProgramService programService;
    private final ProgramCreationService programCreationService;

    public ProgramController(ProgramService programService, ProgramCreationService programCreationService) {
        this.programService = programService;
        this.programCreationService = programCreationService;
    }

    @GetMapping
    public List<ProgramDTO> getPrograms() {
        return programService.getAll();
    }

    @GetMapping("/{id}")
    public ProgramDTO getProgram(@PathVariable Long id) {
        return programService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramDTO createProgram(@RequestBody ProgramDTO dto) {
        return programService.create(dto);
    }

    @PostMapping("/full")
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramCreationResponseDTO createFullProgram(@RequestBody ProgramCreationRequestDTO request) {
        return programCreationService.createFull(request);
    }

    @PutMapping("/{id}")
    public ProgramDTO updateProgram(@PathVariable Long id, @RequestBody ProgramDTO dto) {
        return programService.update(id, dto);
    }

    @PutMapping("/full/{id}")
    public ProgramCreationResponseDTO updateFullProgram(@PathVariable Long id,
                                                        @RequestBody ProgramCreationRequestDTO request) {
        return programCreationService.updateFull(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/full/{id}")
    public ProgramFullDTO getFullProgram(@PathVariable Long id) {
        return programCreationService.getFull(id);
    }
}