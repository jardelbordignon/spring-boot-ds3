package dev.jardel.catalog.dto.category;

import dev.jardel.catalog.domain.category.Category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
