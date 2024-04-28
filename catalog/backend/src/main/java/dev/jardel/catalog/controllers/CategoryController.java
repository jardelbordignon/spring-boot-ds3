package dev.jardel.catalog.controllers;

import dev.jardel.catalog.dto.GetCategoriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jardel.catalog.dto.GetCategoryDto;
import dev.jardel.catalog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

  @Autowired
  private CategoryService service;

  @GetMapping
  public ResponseEntity<GetCategoriesDto> findAll() {
    GetCategoriesDto dto = service.findAll();
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<GetCategoryDto> findById(@PathVariable Long id) {
    GetCategoryDto dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }

}
