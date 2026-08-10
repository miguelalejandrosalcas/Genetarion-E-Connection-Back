package com.alumni.dto;

import com.alumni.enums.CategoryType;
import com.alumni.enums.Section;
import com.alumni.model.Category;
import com.fasterxml.jackson.annotation.JsonCreator;

public class CategoryDTO {
    private Long id;
    private CategoryType categoryType;
    private String description;
    private Boolean active;
    private Section section;

    @JsonCreator
    public CategoryDTO() {
    }

    public CategoryDTO(Long id, CategoryType categoryType, String description, Boolean active) {
        this.id = id;
        this.categoryType = categoryType;
        this.description = description;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Section getSection() { return section; }
    public void setSection(Section section) { this.section = section; }

    public static CategoryDTO fromEntity(Category category) {
        CategoryDTO dto = new CategoryDTO(
                category.getId(),
                category.getCategoryType(),
                category.getDescription(),
                category.isActive()
        );
        dto.setSection(category.getSection());
        return dto;
    }


    public Category toEntity() {
        return new Category(
                this.categoryType,
                this.description,
                this.section,
                this.active != null ? this.active : true);
    }
}