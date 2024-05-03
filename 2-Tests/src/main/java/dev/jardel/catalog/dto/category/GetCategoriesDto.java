package dev.jardel.catalog.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetCategoriesDto {
    @JsonProperty("categories")
    private List<CategoryDto> categoryDtos;

    public GetCategoriesDto(List<CategoryDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }
}
