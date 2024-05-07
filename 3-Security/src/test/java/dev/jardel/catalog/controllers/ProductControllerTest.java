package dev.jardel.catalog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jardel.catalog.config.DatabaseException;
import dev.jardel.catalog.domain.product.exceptions.ProductNotFoundException;
import dev.jardel.catalog.dto.product.GetProductDto;
import dev.jardel.catalog.dto.product.ProductDto;
import dev.jardel.catalog.services.ProductService;
import dev.jardel.catalog.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;
    private ProductDto productDto;
    private GetProductDto getProductDto;
    private PageImpl<ProductDto> page;

    @BeforeEach()
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;
        productDto = Factory.createProductDto();
        getProductDto = new GetProductDto(productDto);
        page = new PageImpl<>(List.of(productDto));
        when(service.findAllPaged(any())).thenReturn(page);
        when(service.findById(existingId)).thenReturn(getProductDto);
        when(service.findById(nonExistingId)).thenThrow(ProductNotFoundException.class);
        when(service.create(any())).thenReturn(productDto);
        when(service.update(eq(nonExistingId), any())).thenThrow(ProductNotFoundException.class);
        when(service.update(eq(existingId), any())).thenReturn(getProductDto);
        when(service.update(eq(nonExistingId), any())).thenThrow(ProductNotFoundException.class);
        doNothing().when(service).delete(existingId);
        doThrow(ProductNotFoundException.class).when(service).delete(nonExistingId);
        doThrow(DatabaseException.class).when(service).delete(dependentId);

    }

    @Test
    public void deleteShouldReturnNoContentWhenIdExists() throws Exception{
        ResultActions result = mockMvc.perform(delete("/products/{id}", existingId).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNoContent());

    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExists() throws Exception{
        ResultActions result = mockMvc.perform(delete("/products/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }

    @Test
    public void createShouldReturnProductDtoCreated() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(productDto);

        ResultActions result = mockMvc.perform(post("/products")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
    }


    @Test
    public void updateShouldReturnProductDtoWhenIdExists() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(productDto);

        ResultActions result = mockMvc.perform(put("/products/" + existingId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.product.id").exists());
        result.andExpect(jsonPath("$.product.name").exists());
        result.andExpect(jsonPath("$.product.description").exists());
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdNotExists() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(productDto);

        ResultActions result = mockMvc.perform(put("/products/{id}", nonExistingId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());

    }

    @Test
    public void findByIdShouldReturnProductWhenIdExists() throws Exception{
        ResultActions result = mockMvc.perform(get("/products/{id}", existingId)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        // response body
        System.out.println(result.andReturn().getResponse().getContentAsString());
        result.andExpect(jsonPath("$.product.id").exists());
        result.andExpect(jsonPath("$.product.name").exists());
        result.andExpect(jsonPath("$.product.description").exists());
    }

    @Test
    public void findByIdShouldThrowProductNotFoundExceptionWhenIdNotExists() throws Exception{
        ResultActions result = mockMvc.perform(get("/products/{id}", nonExistingId)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }

    @Test
    public void findAllShouldReturnPage() throws Exception{
        ResultActions result = mockMvc.perform(get("/products")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
    
}
