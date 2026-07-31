package com.alumni.dto;

import com.alumni.model.UploadedFiles;

import java.time.LocalDateTime;

public class UploadedFilesDTO {
    private Long id;
    private Administrator administrator;
    private String fileName;
    private String storageUrl;
    private String mimeType;
    private Long sizeBytes;
    private LocalDateTime createdAt;

    public UploadedFilesDTO(){
    }

    public UploadedFilesDTO(Long id, Administrator administrator, String fileName, String storageUrl, String mimeType, Long sizeBytes, LocalDateTime createdAt){
        this.id = id;
        this.administrator = administrator;
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

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
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

    public static UploadedFilesDTO fromEntity(UploadedFiles uploadedFiles){
        return new UploadedFilesDTO(
                uploadedFiles.getId(),
                uploadedFiles.getAdministrator(),
                uploadedFiles.getFileName(),
                uploadedFiles.getStorageUrl(),
                uploadedFiles.getMimeType(),
                uploadedFiles.getSizeBytes(),
                uploadedFiles.getCreatedAt()
        );
    }

    public UploadedFiles toEntity(){
        return new UploadedFiles(this.administrator,this.fileName,this.storageUrl,this.mimeType,this.sizeBytes,this.createdAt);
    }
}
