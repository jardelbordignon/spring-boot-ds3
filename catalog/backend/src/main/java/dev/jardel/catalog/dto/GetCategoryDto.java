package dev.jardel.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCategoryDto {
    @JsonProperty("category")
    private CategoryDto categoryDto;

    public GetCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
