package com.alumni.controller;

import com.alumni.dto.JobBoardDTO;
import com.alumni.service.JobBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-boards")
public class JobBoardController {

    private final JobBoardService jobBoardService;

    public JobBoardController(JobBoardService jobBoardService) {
        this.jobBoardService = jobBoardService;
    }

    @GetMapping
    public List<JobBoardDTO> getJobBoards(
            @RequestParam(required = false, defaultValue = "false") boolean includeInactive) {
        return includeInactive ? jobBoardService.getAll() : jobBoardService.getAllActive();
    }

    @GetMapping("/{id}")
    public JobBoardDTO getJobBoard(@PathVariable Long id) {
        return jobBoardService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobBoardDTO createJobBoard(@RequestBody JobBoardDTO dto) {
        return jobBoardService.create(dto);
    }

    @PutMapping("/{id}")
    public JobBoardDTO updateJobBoard(@PathVariable Long id, @RequestBody JobBoardDTO dto) {
        return jobBoardService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobBoard(@PathVariable Long id) {
        jobBoardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}