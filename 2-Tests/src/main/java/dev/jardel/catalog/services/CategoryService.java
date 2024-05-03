package dev.jardel.catalog.services;

import dev.jardel.catalog.config.DatabaseException;
import dev.jardel.catalog.domain.category.exceptions.CategoryNotFoundException;
import dev.jardel.catalog.dto.category.CategoryDto;
import dev.jardel.catalog.dto.category.GetCategoriesDto;
import dev.jardel.catalog.dto.category.GetCategoryDto;
import dev.jardel.catalog.domain.category.Category;
import dev.jardel.catalog.repositories.CategoryRepository;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true) // Read only transaction to avoid concurrency problems in the database access (only read operations)
    public GetCategoriesDto findAll() {
        List<Category> categories = repository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(CategoryDto::new).toList();
        return new GetCategoriesDto(categoryDtos);
    }

    @Transactional(readOnly = true) // Read only transaction to avoid concurrency problems in the database access (only read operations)
    public Page<CategoryDto> findAllPaged(Pageable pageable) {
        Page<Category> categories = repository.findAll(pageable);
        return categories.map(CategoryDto::new);
    }

    @Transactional(readOnly = true)
    public GetCategoryDto findById(Long id) {
        Category category = this.repository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        CategoryDto categoryDto = new CategoryDto(category);
        return new GetCategoryDto(categoryDto);
    }

    @Transactional
    public CategoryDto create(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category = this.repository.save(category);
        return new CategoryDto(category);
    }

    @Transactional
    public GetCategoryDto update(Long id, CategoryDto dto) {
        try {
            Category category = this.repository.getReferenceById(id);
            category.setName(dto.getName());
            category = this.repository.save(category);
            dto = new CategoryDto(category);
            return new GetCategoryDto(dto);
        } catch (EntityNotFoundException e) {
            throw new CategoryNotFoundException("Category not found with id: " + id);
        }
    }

    public void delete(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new CategoryNotFoundException("Category not found with id: " + id);
            }
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Category with id: " + id + " cannot be deleted because it is in use by other entities.\n" + e.getMessage());
        }
    }
}
