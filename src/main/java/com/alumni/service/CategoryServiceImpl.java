package com.alumni.service;

import com.alumni.dto.CategoryDTO;
import com.alumni.model.Category;
import com.alumni.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllActive() {
        return categoryRepository.findByActiveTrue().stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getById(Long id) {
        return CategoryDTO.fromEntity(findEntityOrThrow(id));
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setActive(true);
        return CategoryDTO.fromEntity(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category category = findEntityOrThrow(id);
        if (dto.getName() != null) category.setName(dto.getName());
        if (dto.getDescription() != null) category.setDescription(dto.getDescription());
        category.setActive(dto.getActive());
        return CategoryDTO.fromEntity(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(findEntityOrThrow(id));
    }

    private Category findEntityOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe una categoría con id " + id));
    }
}