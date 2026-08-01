package com.alumni.dto;

import com.alumni.model.Category;

public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private int active;

    public CategoryDTO(Long id, String name, String description, int active){
    }

    public CategoryDTO(String name, String description, int active){
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public static CategoryDTO fromEntity(Category category){
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getActive()
        );
    }

    public Category toEntity(){
        return new Category(this.name,this.description,this.active);
    }
}