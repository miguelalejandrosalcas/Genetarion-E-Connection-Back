package com.alumni.dto;

import com.alumni.enums.Section;
import com.alumni.model.Resource;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ResourceDTO {
    private Long id;
    private Long categoryId;
    private Long resourceTypeId;
    private Long administratorId;
    private String title;
    private String description;
    private Section section;
    private String url;
    private Integer durationMinutes;
    private LocalDate publicationDate;
    private Boolean featured;
    private Boolean active;
    private Integer views;
    private Integer downloads;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonCreator
    public ResourceDTO() {
    }

    public ResourceDTO(Long id, Long categoryId, Long resourceTypeId, Long administratorId,
                       String title, String description, Section section, String url,
                       Integer durationMinutes, LocalDate publicationDate, Boolean featured,
                       Boolean active, Integer views, Integer downloads,
                       LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.categoryId = categoryId;
        this.resourceTypeId = resourceTypeId;
        this.administratorId = administratorId;
        this.title = title;
        this.description = description;
        this.section = section;
        this.url = url;
        this.durationMinutes = durationMinutes;
        this.publicationDate = publicationDate;
        this.featured = featured;
        this.active = active;
        this.views = views;
        this.downloads = downloads;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getResourceTypeId() {
        return resourceTypeId;
    }

    public void setResourceTypeId(Long resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }

    public Long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Boolean isFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static ResourceDTO fromEntity(Resource resource) {
        return new ResourceDTO(
                resource.getId(),
                resource.getCategory() != null ? resource.getCategory().getId() : null,
                resource.getResourceType() != null ? resource.getResourceType().getId() : null,
                resource.getAdministrator() != null ? resource.getAdministrator().getId() : null,
                resource.getTitle(),
                resource.getDescription(),
                resource.getSection(),
                resource.getUrl(),
                resource.getDurationMinutes(),
                resource.getPublicationDate(),
                resource.isFeatured(),
                resource.isActive(),
                resource.getViews(),
                resource.getDownloads(),
                resource.getCreatedAt(),
                resource.getUpdatedAt()
        );
    }

    public Resource toEntity() {
        Resource resource = new Resource();
        resource.setTitle(this.title);
        resource.setDescription(this.description);
        resource.setSection(this.section);
        resource.setUrl(this.url);
        resource.setDurationMinutes(this.durationMinutes);
        resource.setPublicationDate(this.publicationDate);
        resource.setFeatured(this.featured);
        resource.setActive(this.active);
        resource.setViews(this.views);
        resource.setDownloads(this.downloads);
        resource.setCreatedAt(this.createdAt);
        resource.setUpdatedAt(this.updatedAt);
        return resource;
    }
}