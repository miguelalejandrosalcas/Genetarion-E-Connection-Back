package com.alumni.dto;

import com.alumni.model.ResourceType;

import java.util.ArrayList;

public class ResourceTypeDTO {
    private Long id;
    private String name;

    public ResourceTypeDTO() {
    }

    public ResourceTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public static ResourceTypeDTO fromEntity(ResourceType resourceType) {
        return new ResourceTypeDTO(
                resourceType.getId(),
                resourceType.getName()
        );
    }

    public ResourceType toEntity() {
        return new ResourceType(this.name, new ArrayList<>());
    }
}