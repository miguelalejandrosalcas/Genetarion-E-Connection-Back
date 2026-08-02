package com.alumni.service;

import com.alumni.dto.UploadedFilesDTO;
import com.alumni.model.Administrator;
import com.alumni.model.UploadedFile;
import com.alumni.repository.AdministratorRepository;
import com.alumni.repository.UploadedFilesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadedFilesServiceImpl implements UploadedFilesService {

    private final UploadedFilesRepository uploadedFilesRepository;
    private final AdministratorRepository administratorRepository;

    public UploadedFilesServiceImpl(UploadedFilesRepository uploadedFilesRepository,
                                    AdministratorRepository administratorRepository) {
        this.uploadedFilesRepository = uploadedFilesRepository;
        this.administratorRepository = administratorRepository;
    }

    @Override
    public List<UploadedFilesDTO> getAll() {
        return uploadedFilesRepository.findAll().stream()
                .map(UploadedFilesDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UploadedFilesDTO getById(Long id) {
        return UploadedFilesDTO.fromEntity(findEntityOrThrow(id));
    }

    @Override
    public UploadedFilesDTO create(UploadedFilesDTO dto) {
        if (dto.getAdministratorId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "administratorId es obligatorio");
        }
        if (dto.getStorageUrl() == null || dto.getStorageUrl().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "storageUrl es obligatorio");
        }

        Administrator administrator = findAdministratorOrThrow(dto.getAdministratorId());

        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setAdministrator(administrator);
        uploadedFile.setFileName(dto.getFileName());
        uploadedFile.setStorageUrl(dto.getStorageUrl());
        uploadedFile.setMimeType(dto.getMimeType());
        uploadedFile.setSizeBytes(dto.getSizeBytes());
        uploadedFile.setCreatedAt(LocalDateTime.now());

        return UploadedFilesDTO.fromEntity(uploadedFilesRepository.save(uploadedFile));
    }

    @Override
    public UploadedFilesDTO update(Long id, UploadedFilesDTO dto) {
        UploadedFile uploadedFile = findEntityOrThrow(id);

        if (dto.getAdministratorId() != null) {
            uploadedFile.setAdministrator(findAdministratorOrThrow(dto.getAdministratorId()));
        }
        if (dto.getFileName() != null) uploadedFile.setFileName(dto.getFileName());
        if (dto.getStorageUrl() != null) uploadedFile.setStorageUrl(dto.getStorageUrl());
        if (dto.getMimeType() != null) uploadedFile.setMimeType(dto.getMimeType());
        if (dto.getSizeBytes() != null) uploadedFile.setSizeBytes(dto.getSizeBytes());

        return UploadedFilesDTO.fromEntity(uploadedFilesRepository.save(uploadedFile));
    }

    @Override
    public void delete(Long id) {
        uploadedFilesRepository.delete(findEntityOrThrow(id));
    }

    private UploadedFile findEntityOrThrow(Long id) {
        return uploadedFilesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe un archivo con id " + id));
    }

    private Administrator findAdministratorOrThrow(Long administratorId) {
        return administratorRepository.findById(administratorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "No existe un administrador con id " + administratorId));
    }
}