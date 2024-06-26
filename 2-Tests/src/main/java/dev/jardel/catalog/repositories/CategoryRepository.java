package dev.jardel.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.jardel.catalog.domain.category.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
