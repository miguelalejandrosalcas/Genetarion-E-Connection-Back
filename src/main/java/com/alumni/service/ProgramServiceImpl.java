package com.alumni.service;

import com.alumni.dto.ProgramDTO;
import com.alumni.model.Program;
import com.alumni.repository.ProgramRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<ProgramDTO> getAll() {
        return programRepository.findAll().stream()
                .map(ProgramDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramDTO getById(Long id) {
        return ProgramDTO.fromEntity(findEntityOrThrow(id));
    }

    @Override
    public ProgramDTO create(ProgramDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
        Program program = new Program();
        program.setName(dto.getName());
        program.setDescription(dto.getDescription());
        return ProgramDTO.fromEntity(programRepository.save(program));
    }

    @Override
    public ProgramDTO update(Long id, ProgramDTO dto) {
        Program program = findEntityOrThrow(id);
        if (dto.getName() != null) program.setName(dto.getName());
        if (dto.getDescription() != null) program.setDescription(dto.getDescription());
        return ProgramDTO.fromEntity(programRepository.save(program));
    }

    @Override
    public void delete(Long id) {
        programRepository.delete(findEntityOrThrow(id));
    }

    private Program findEntityOrThrow(Long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe un programa con id " + id));
    }
}