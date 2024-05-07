package dev.jardel.catalog.services;

import java.util.List;
import java.util.Optional;

import dev.jardel.catalog.domain.product.Product;
import dev.jardel.catalog.domain.product.exceptions.ProductNotFoundException;
import dev.jardel.catalog.dto.product.ProductDto;
import dev.jardel.catalog.repositories.ProductRepository;

import dev.jardel.catalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository productRepository;

    private Long existingId;
    private Long nonExistingId;
    private Product product1;
    private PageImpl<Product> pagedProducts;

    @BeforeEach
    public void setUp() {
        existingId = 1L;
        nonExistingId = 1000L;

        product1 = Factory.createProduct("Product 1");

        List<Product> products = List.of(product1, Factory.createProduct("Product 2"));
        pagedProducts = new PageImpl<>(products);

        Mockito.when(productRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(pagedProducts);
        Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(product1);
        Mockito.when(productRepository.findById(existingId)).thenReturn(Optional.of(product1));
        Mockito.when(productRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        Mockito.when(productRepository.existsById(nonExistingId)).thenThrow(ProductNotFoundException.class);
    }

    @Test
    public void findAllPagedShouldReturnPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ProductDto> result = service.findAllPaged(pageable);
        Assertions.assertNotNull(result);
        Mockito.verify(productRepository).findAll(pageable);
    }
}
