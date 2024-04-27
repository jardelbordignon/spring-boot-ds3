package dev.jardel.catalog.entities;

import dev.jardel.catalog.entities.utils.EntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends EntityId {

  private String name;

  public Category() {}

  public Category(Long id, String name) {
    this.setId(id);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
