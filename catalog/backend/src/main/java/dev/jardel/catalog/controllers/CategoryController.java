package dev.jardel.catalog.controllers;

import com.fasterxml.jackson.core.TreeNode;
import dev.jardel.catalog.dto.category.CategoryDto;
import dev.jardel.catalog.dto.category.GetCategoriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.jardel.catalog.dto.category.GetCategoryDto;
import dev.jardel.catalog.services.CategoryService;
import org.springframework.web.util.UriComponentsBuilder;

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

  @PostMapping
  public ResponseEntity<GetCategoryDto> create(@RequestBody CategoryDto dto, UriComponentsBuilder uriComponentsBuilder) {
    dto = service.create(dto);
    var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }
}
