package com.alumni.dto;

import com.alumni.model.JobBoard;

public class JobBoardDTO {
    private Long id;
    private String name;
    private String url;
    private String description;
    private String logoUrl;
    private boolean active;

    public JobBoardDTO() {
    }

    public JobBoardDTO(Long id, String name, String url, String description,
                       String logoUrl, boolean active) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.logoUrl = logoUrl;
        this.active = active;
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static JobBoardDTO fromEntity(JobBoard jobBoard) {
        return new JobBoardDTO(
                jobBoard.getId(),
                jobBoard.getName(),
                jobBoard.getUrl(),
                jobBoard.getDescription(),
                jobBoard.getLogoUrl(),
                jobBoard.isActive()
        );
    }

    public JobBoard toEntity() {
        return new JobBoard(this.name, this.url, this.description, this.logoUrl, this.active);
    }
}