package dev.jardel.catalog.controllers;

import dev.jardel.catalog.dto.product.ProductDto;
import dev.jardel.catalog.dto.product.GetProductDto;
import dev.jardel.catalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

  @Autowired
  private ProductService service;

  @GetMapping
  public ResponseEntity<Page<ProductDto>> findAll(
          @RequestParam(value = "page", defaultValue = "0") Integer page,
          @RequestParam(value = "perPage", defaultValue = "15") Integer perPage,
          @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
          @RequestParam(value = "direction", defaultValue = "ASC") String direction
  ) {
    Page<ProductDto> dto = service.findAllPaged(page, perPage, orderBy, direction);
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<GetProductDto> findById(@PathVariable Long id) {
    GetProductDto dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }

  @PostMapping
  public ResponseEntity<GetProductDto> create(@RequestBody ProductDto dto, UriComponentsBuilder uriComponentsBuilder) {
    dto = service.create(dto);
    var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<GetProductDto> update(@PathVariable Long id, @RequestBody ProductDto reqDto) {
    GetProductDto dto = service.update(id, reqDto);
    return ResponseEntity.ok().body(dto);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
