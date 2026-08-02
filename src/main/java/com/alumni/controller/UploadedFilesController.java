package com.alumni.controller;

import com.alumni.dto.UploadedFilesDTO;
import com.alumni.service.UploadedFilesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/uploaded-files")
public class UploadedFilesController {

    private final UploadedFilesService uploadedFilesService;

    public UploadedFilesController(UploadedFilesService uploadedFilesService) {
        this.uploadedFilesService = uploadedFilesService;
    }

    @GetMapping
    public List<UploadedFilesDTO> getUploadedFiles() {
        return uploadedFilesService.getAll();
    }

    @GetMapping("/{id}")
    public UploadedFilesDTO getUploadedFile(@PathVariable Long id) {
        return uploadedFilesService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadedFilesDTO createUploadedFile(@RequestBody UploadedFilesDTO dto) {
        return uploadedFilesService.create(dto);
    }

    @PutMapping("/{id}")
    public UploadedFilesDTO updateUploadedFile(@PathVariable Long id, @RequestBody UploadedFilesDTO dto) {
        return uploadedFilesService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUploadedFile(@PathVariable Long id) {
        uploadedFilesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}