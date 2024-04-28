package dev.jardel.catalog.services;

import dev.jardel.catalog.dto.CategoryDto;
import dev.jardel.catalog.dto.GetCategoriesDto;
import dev.jardel.catalog.dto.GetCategoryDto;
import dev.jardel.catalog.entities.Category;
import dev.jardel.catalog.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true) // Read only transaction to avoid concurrency problems in the database access (only read operations)
    public GetCategoriesDto findAll() {
        List<Category> categories = repository.findAll();
        // List<CategoryDto> categoryDtos = categories.stream().map(cat -> new CategoryDto(cat)).toList();
        List<CategoryDto> categoryDtos = categories.stream().map(CategoryDto::new).toList();
        return new GetCategoriesDto(categoryDtos);
    }

    @Transactional(readOnly = true)
    public GetCategoryDto findById(Long id) {
        Optional<Category> categoryObj = repository.findById(id);
        if (categoryObj.isEmpty()) {
            throw new IllegalArgumentException("Category not found with id: " + id);
        }

        CategoryDto categoryDto = new CategoryDto(categoryObj.get());
        return new GetCategoryDto(categoryDto);
    }
}
