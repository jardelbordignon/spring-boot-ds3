package dev.jardel.catalog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jardel.catalog.entities.Category;
import dev.jardel.catalog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

  @Autowired
  private CategoryService service;

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    List<Category> categories = service.findAll();
    return ResponseEntity.ok().body(categories);
  }

}
