package com.alumni.service;

import com.alumni.dto.JobBoardDTO;
import com.alumni.model.JobBoard;
import com.alumni.repository.JobBoardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobBoardServiceImpl implements JobBoardService {

    private final JobBoardRepository jobBoardRepository;

    public JobBoardServiceImpl(JobBoardRepository jobBoardRepository) {
        this.jobBoardRepository = jobBoardRepository;
    }

    @Override
    public List<JobBoardDTO> getAll() {
        return toDtoList(jobBoardRepository.findAll());
    }

    @Override
    public List<JobBoardDTO> getAllActive() {
        return toDtoList(jobBoardRepository.findByActiveTrue());
    }

    @Override
    public JobBoardDTO getById(Long id) {
        return JobBoardDTO.fromEntity(findEntityOrThrow(id));
    }

    @Override
    public JobBoardDTO create(JobBoardDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
        if (dto.getUrl() == null || dto.getUrl().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La URL es obligatoria");
        }
        if (dto.getDescription() == null || dto.getDescription().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La descripción es obligatoria");
        }
        if (dto.getLogoUrl() == null || dto.getLogoUrl().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El logo es obligatorio");
        }

        if (dto.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La categoría es obligatoria (global, local o remota)");
        }

        JobBoard jobBoard = new JobBoard();
        jobBoard.setName(dto.getName());
        jobBoard.setUrl(dto.getUrl());
        jobBoard.setDescription(dto.getDescription());
        jobBoard.setLogoUrl(dto.getLogoUrl());
        jobBoard.setActive(true);
        jobBoard.setCategory(dto.getCategory());

        return JobBoardDTO.fromEntity(jobBoardRepository.save(jobBoard));
    }

    @Override
    public JobBoardDTO update(Long id, JobBoardDTO dto) {
        JobBoard jobBoard = findEntityOrThrow(id);

        if (dto.getName() != null) jobBoard.setName(dto.getName());
        if (dto.getUrl() != null) jobBoard.setUrl(dto.getUrl());
        if (dto.getDescription() != null) jobBoard.setDescription(dto.getDescription());
        if (dto.getLogoUrl() != null) jobBoard.setLogoUrl(dto.getLogoUrl());
        if (dto.getCategory() != null) jobBoard.setCategory(dto.getCategory());
        if (dto.getActive() != null) jobBoard.setActive(dto.getActive());

        return JobBoardDTO.fromEntity(jobBoardRepository.save(jobBoard));
    }

    @Override
    public void delete(Long id) {
        jobBoardRepository.delete(findEntityOrThrow(id));
    }

    private JobBoard findEntityOrThrow(Long id) {
        return jobBoardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe una bolsa de empleo con id " + id));
    }

    private List<JobBoardDTO> toDtoList(List<JobBoard> jobBoards) {
        return jobBoards.stream()
                .map(JobBoardDTO::fromEntity)
                .collect(Collectors.toList());
    }
}