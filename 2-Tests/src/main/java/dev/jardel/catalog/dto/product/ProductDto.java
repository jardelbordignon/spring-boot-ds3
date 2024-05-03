package dev.jardel.catalog.dto.product;

import dev.jardel.catalog.domain.category.Category;
import dev.jardel.catalog.domain.product.Product;
import dev.jardel.catalog.dto.category.CategoryDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Date createdAt;
    private Date updatedAt;

    private List<CategoryDto> categories = new ArrayList<CategoryDto>();

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
    }
    public ProductDto(Product product, Set<Category> categories) {
        this(product);
        categories.forEach(category -> this.categories.add(new CategoryDto(category)));
    }

}
