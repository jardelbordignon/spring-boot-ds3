package dev.jardel.catalog.domain.product;

import dev.jardel.catalog.domain.category.Category;
import dev.jardel.catalog.domain.utils.EntityBase;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends EntityBase implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private String imgUrl;

    @ManyToMany
    @JoinTable(
        name = "categories_products",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<Category> categories = new HashSet<>();
    // O Set é semelhante ao List, mas não aceita elementos duplicados
    // sobre o JoinColumn, o joinColumns vai referenciar a classe em si,
    // e o inverseJoinColumns vai referenciar o tipo da classe informada no Set.
    // nesse caso joinColumns -> Product e inverseJoinColumns -> Category

    public Product(String name, String description, Double price, String imgUrl, Set<Category> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.categories = categories;
    }

    public Product(Long id, String name, String description, Double price, String imgUrl, Set<Category> categories) {
        this.setId(id);
        new Product(name, description, price, imgUrl, categories);
    }
}
