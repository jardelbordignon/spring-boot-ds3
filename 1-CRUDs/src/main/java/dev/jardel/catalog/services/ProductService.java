package dev.jardel.catalog.services;

import dev.jardel.catalog.config.DatabaseException;
import dev.jardel.catalog.domain.category.Category;
import dev.jardel.catalog.domain.product.Product;
import dev.jardel.catalog.dto.category.CategoryDto;
import dev.jardel.catalog.dto.product.ProductDto;
import dev.jardel.catalog.dto.product.GetProductDto;
import dev.jardel.catalog.repositories.CategoryRepository;
import dev.jardel.catalog.repositories.ProductRepository;
import dev.jardel.catalog.domain.product.exceptions.ProductNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDto> findAllPaged(Integer page, Integer perPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, perPage, Direction.valueOf(direction), orderBy);

        Page<Product> products = productRepository.findAll(pageRequest);
        return products.map(ProductDto::new);
    }

    @Transactional(readOnly = true)
    public GetProductDto findById(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        ProductDto productDto = new ProductDto(product, product.getCategories());
        return new GetProductDto(productDto);
    }

    @Transactional
    public ProductDto create(ProductDto dto) {
        Product entity = new Product();
        entity = productRepository.save(handleCopyDtoToEntity(dto, entity));
        return new ProductDto(entity);
    }

    @Transactional
    public GetProductDto update(Long id, ProductDto dto) {
        try {
            Product entity = productRepository.getReferenceById(id);
            entity = productRepository.save(handleCopyDtoToEntity(dto, entity));
            dto = new ProductDto(entity);
            return new GetProductDto(dto);
        } catch (EntityNotFoundException e) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
    }

    public void delete(Long id) {
        try {
            if (!productRepository.existsById(id)) {
                throw new ProductNotFoundException("Product not found with id: " + id);
            }
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Product with id: " + id + " cannot be deleted because it is in use by other entities.\n" + e.getMessage());
        }
    }

    private Product handleCopyDtoToEntity(ProductDto dto, Product entity) {
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getImgUrl() != null) entity.setImgUrl(dto.getImgUrl());
        if (dto.getCreatedAt() != null) entity.setCreatedAt(dto.getCreatedAt());
        if (dto.getUpdatedAt() != null) entity.setUpdatedAt(dto.getUpdatedAt());

        entity.getCategories().clear();
        for (CategoryDto categoryDto : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(categoryDto.getId());
            entity.getCategories().add(category);
        }

        return entity;
    }
}
