package dev.jardel.catalog.tests;

import dev.jardel.catalog.domain.category.Category;
import dev.jardel.catalog.domain.product.Product;
import dev.jardel.catalog.dto.product.ProductDto;

public class Factory {
    public static Product createProduct(String name) {
        Product product = new Product();
        product.setId(1L);
        product.setName(name);
        product.setDescription(name +" description");
        product.setPrice(100.0);
        product.setImgUrl("ImgUrl");
        return product;
    }

    public static ProductDto createProductDto() {
        Product product = createProduct("Product 1");
        return new ProductDto(product, product.getCategories());
    }

    public static Category createCategory() {
        return new Category("Category 1");
    }
}
