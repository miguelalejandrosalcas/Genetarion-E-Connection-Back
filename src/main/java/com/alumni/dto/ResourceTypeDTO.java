package com.alumni.dto;

import com.alumni.model.ResourceType;

import java.util.ArrayList;

public class ResourceTypeDTO {
    private long id;
    private String name;

    public ResourceTypeDTO() {
    }

    public ResourceTypeDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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