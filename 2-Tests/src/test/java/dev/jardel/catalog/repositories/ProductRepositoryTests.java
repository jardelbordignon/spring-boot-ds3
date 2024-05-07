package dev.jardel.catalog.repositories;

import dev.jardel.catalog.domain.product.Product;
import dev.jardel.catalog.tests.Factory;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@RequiredArgsConstructor
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;
    private Long existingId;
    private Long nonExistingId;
    private Long countProducts;

    @BeforeEach
    public void setUp() {
        existingId = 1L;
        nonExistingId = 100L;
        countProducts = productRepository.count();
    }

    @Test
    public void shouldBeAbleToDeleteProductById() {
        productRepository.deleteById(existingId);
        Assertions.assertTrue(productRepository.findById(existingId).isEmpty());
    }

    @Test
    public void shouldNotBeAbleToDeleteANonExistentProduct() {
        Number productsCount = productRepository.count();
        productRepository.deleteById(nonExistingId);
        Assertions.assertEquals(productsCount, productRepository.count());
        //Assertions.assertThrows(DatabaseException.class, () -> productRepository.deleteById(nonExistentId));
    }

    @Test
    public void saveMethodShouldPersistANewProductWhitAutoIncrementedId() {
        Product product = Factory.createProduct("Product 1");
        product.setId(null);
        product = productRepository.save(product);
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countProducts + 1, product.getId());
    }

    @Test
    public void saveMethodShouldUpdateAnExistingProductWhenIdIsNotNull() {
        Product product = Factory.createProduct("Product 1");
        product.setId(existingId);
        product.setName("Product 1 Updated");
        product = productRepository.save(product);
        Assertions.assertEquals(existingId, product.getId());
        Assertions.assertEquals("Product 1 Updated", product.getName());
    }

    @Test
    public void findByIdShouldBeAbleToFindProduct() {
        Product product = productRepository.findById(existingId).get();
        Assertions.assertNotNull(product);
        Assertions.assertEquals(existingId, product.getId());
    }

    @Test
    public void findByIdShouldNotBeAbleToFindANonExistentProduct() {
        Assertions.assertFalse(productRepository.findById(nonExistingId).isPresent());
    }

    @Test
    public void findAllShouldBeAbleToFindProducts() {
        Assertions.assertFalse(productRepository.findAll().isEmpty());
    }

    @Test
    public void deleteShouldBeAbleToDeleteProduct() {
        productRepository.deleteById(existingId);
        Assertions.assertTrue(productRepository.findById(existingId).isEmpty());
    }

    @Test
    public void deleteShouldNotBeAbleToDeleteANonExistentProduct() {
        productRepository.deleteById(nonExistingId);
        Assertions.assertFalse(productRepository.findById(nonExistingId).isPresent());
    }

}
