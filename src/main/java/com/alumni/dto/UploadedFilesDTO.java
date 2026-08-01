package com.alumni.dto;

import com.alumni.model.UploadedFile;

import java.time.LocalDateTime;

public class UploadedFilesDTO {
    private Long id;
    private String fileName;
    private String storageUrl;
    private String mimeType;
    private Long sizeBytes;
    private LocalDateTime createdAt;

    public UploadedFilesDTO(){
    }

    public UploadedFilesDTO(Long id, String fileName, String storageUrl, String mimeType, Long sizeBytes, LocalDateTime createdAt){
        this.id = id;
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

    public static UploadedFilesDTO fromEntity(UploadedFile uploadedFile){
        return new UploadedFilesDTO(
                uploadedFile.getId(),
                uploadedFile.getFileName(),
                uploadedFile.getStorageUrl(),
                uploadedFile.getMimeType(),
                uploadedFile.getSizeBytes(),
                uploadedFile.getCreatedAt()
        );
    }

    public UploadedFile toEntity(){
        return new UploadedFile();
    }
}
