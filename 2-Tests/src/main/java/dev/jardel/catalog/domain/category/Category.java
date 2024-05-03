package dev.jardel.catalog.domain.category;

import dev.jardel.catalog.domain.utils.EntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends EntityId {

  private String name;

}
