package com.alumni.service;

import com.alumni.dto.UploadedFilesDTO;

import java.util.List;

public interface UploadedFilesService {
    List<UploadedFilesDTO> getAll();
    UploadedFilesDTO getById(Long id);
    UploadedFilesDTO create(UploadedFilesDTO dto);
    UploadedFilesDTO update(Long id, UploadedFilesDTO dto);
    void delete(Long id);
}