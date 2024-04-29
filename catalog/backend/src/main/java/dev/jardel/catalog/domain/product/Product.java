package dev.jardel.catalog.domain.product;

import dev.jardel.catalog.domain.category.Category;
import dev.jardel.catalog.domain.utils.EntityBase;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
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

    public Product() {}
    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.setId(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
