package dev.jardel.catalog.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetProductDto {
    @JsonProperty("product")
    private ProductDto categoryDto;

    public GetProductDto(ProductDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
