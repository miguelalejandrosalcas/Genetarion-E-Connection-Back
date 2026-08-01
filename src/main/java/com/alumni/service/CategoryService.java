package com.alumni.service;

import com.alumni.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll();
    List<CategoryDTO> getAllActive();
    CategoryDTO getById(Long id);
    CategoryDTO create(CategoryDTO dto);
    CategoryDTO update(Long id, CategoryDTO dto);
    void delete(Long id);
}