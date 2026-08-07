package com.alumni.dto;

import com.alumni.enums.JobCategory;
import com.alumni.model.JobBoard;

public class JobBoardDTO {
    private Long id;
    private String name;
    private String url;
    private String description;
    private String logoUrl;
    private Boolean active;
    private JobCategory category;

    public JobBoardDTO() {
    }

    public JobBoardDTO(Long id, String name, String url, String description,
                       String logoUrl, Boolean active, JobCategory category) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.logoUrl = logoUrl;
        this.active = active;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public JobCategory getCategory() {
        return category;
    }

    public void setCategory(JobCategory category) {
        this.category = category;
    }

    public static JobBoardDTO fromEntity(JobBoard jobBoard) {
        return new JobBoardDTO(
                jobBoard.getId(),
                jobBoard.getName(),
                jobBoard.getUrl(),
                jobBoard.getDescription(),
                jobBoard.getLogoUrl(),
                jobBoard.isActive(),
                jobBoard.getCategory()
        );
    }

    public JobBoard toEntity() {
        return new JobBoard(this.name, this.url, this.description, this.logoUrl, this.active, this.category);
    }
}