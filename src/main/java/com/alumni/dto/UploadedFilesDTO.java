package com.alumni.dto;

import com.alumni.model.UploadedFile;

import java.time.LocalDateTime;

public class UploadedFilesDTO {
    private Long id;
    private Long administratorId;
    private String fileName;
    private String storageUrl;
    private String mimeType;
    private Long sizeBytes;
    private LocalDateTime createdAt;

    public UploadedFilesDTO() {
    }

    public UploadedFilesDTO(Long id, Long administratorId, String fileName, String storageUrl,
                            String mimeType, Long sizeBytes, LocalDateTime createdAt) {
        this.id = id;
        this.administratorId = administratorId;
        this.fileName = fileName;
        this.storageUrl = storageUrl;
        this.mimeType = mimeType;
        this.sizeBytes = sizeBytes;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStorageUrl() {
        return storageUrl;
    }

    public void setStorageUrl(String storageUrl) {
        this.storageUrl = storageUrl;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getSizeBytes() {
        return sizeBytes;
    }

    public void setSizeBytes(Long sizeBytes) {
        this.sizeBytes = sizeBytes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static UploadedFilesDTO fromEntity(UploadedFile uploadedFile) {
        return new UploadedFilesDTO(
                uploadedFile.getId(),
                uploadedFile.getAdministrator() != null ? uploadedFile.getAdministrator().getId() : null,
                uploadedFile.getFileName(),
                uploadedFile.getStorageUrl(),
                uploadedFile.getMimeType(),
                uploadedFile.getSizeBytes(),
                uploadedFile.getCreatedAt()
        );
    }

    public UploadedFile toEntity() {
        return new UploadedFile();
    }
}