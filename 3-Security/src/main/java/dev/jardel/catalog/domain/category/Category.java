package dev.jardel.catalog.domain.category;

import dev.jardel.catalog.domain.product.Product;
import dev.jardel.catalog.domain.utils.EntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends EntityBase {

  private String name;

  @ManyToMany(mappedBy = "categories") // mappedBy -> nome do atributo na classe de associação, neste caso o atributo categories na classe Product
  private Set<Product> products = new HashSet<>();
}
