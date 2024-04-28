package dev.jardel.catalog.services;

import dev.jardel.catalog.entities.Category;
import dev.jardel.catalog.repositories.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true) // Read only transaction to avoid concurrency problems in the database access (only read operations)
    public List<Category> findAll() {
        return repository.findAll();
    }
}
