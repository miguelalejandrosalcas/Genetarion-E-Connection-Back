package com.alumni.controller;

import com.alumni.dto.CategoryDTO;
import com.alumni.dto.JobBoardDTO;
import com.alumni.service.CategoryService;
import com.alumni.service.JobBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<CategoryDTO> getCategories(
            @RequestParam(required = false) String section,
            @RequestParam(required = false, defaultValue = "false") boolean includeInactive) {

        if (section != null && !section.isBlank()) {
            return categoryService.getAllActiveBySection(section);
        }
        return includeInactive ? categoryService.getAll() : categoryService.getAllActive();
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategory(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@RequestBody CategoryDTO dto) {
        return categoryService.create(dto);
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}