package dev.jardel.catalog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jardel.catalog.entities.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    List<Category> categories = new ArrayList<>();
    categories.add(new Category(1L, "Books"));
    categories.add(new Category(2L, "Electronics"));
    categories.add(new Category(3L, "Computers"));
    return ResponseEntity.ok().body(categories);
  }

}
