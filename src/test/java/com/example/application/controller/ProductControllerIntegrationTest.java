package com.example.application.controller;

import com.example.application.domain.Product;
import com.example.application.repository.ProductMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerIntegrationTest {
    private Product firstProduct;
    private Product secondProduct;
    private Product productToSave;
    private Product productToSave2;

    @Autowired
    ProductMongoRepository productMongoRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        productToSave = new Product(1, "brand", "description1", "image1", 100);
        productToSave2 = new Product(2, "brazil", "description2", "image2", 200);
        firstProduct = productMongoRepository.save(productToSave);
        secondProduct = productMongoRepository.save(productToSave2);
    }

    @AfterEach
    public void cleanDatabase() {
        productMongoRepository.deleteAll();
        productMongoRepository.deleteById("1");
        productMongoRepository.deleteById("2");
    }

    @Test
    void shouldRetrieveProduct() throws Exception {
        mockMvc.perform(get("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", Matchers.is(firstProduct.getId())))
                .andExpect(jsonPath("brand", Matchers.is(firstProduct.getBrand())))
                .andExpect(jsonPath("description", Matchers.is(firstProduct.getDescription())))
                .andExpect(jsonPath("image", Matchers.is(firstProduct.getImage())))
                .andExpect(jsonPath("price", Matchers.is(firstProduct.getPrice())))
                .andExpect(jsonPath("finalPrice", Matchers.notNullValue()))
                .andExpect(jsonPath("discount", Matchers.notNullValue()));
    }

    @Test
    void shouldRetrieveNotFoundProduct() throws Exception {
        mockMvc.perform(get("/products/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

        @Test
    void shouldReturnProductListByBrand() throws Exception {
        mockMvc.perform(get("/products/search?filter=bra")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(firstProduct.getId())))
                .andExpect(jsonPath("$[0].brand", Matchers.is(firstProduct.getBrand())))
                .andExpect(jsonPath("$[0].description", Matchers.is(firstProduct.getDescription())))
                .andExpect(jsonPath("$[0].image", Matchers.is(firstProduct.getImage())))
                .andExpect(jsonPath("$[0].price", Matchers.is(firstProduct.getPrice())))
                .andExpect(jsonPath("$[0].finalPrice", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].discount", Matchers.notNullValue()));
    }

    @Test
    void shouldReturnProductListByExactDescription() throws Exception {
        mockMvc.perform(get("/products/search?filter=description1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is(firstProduct.getId())))
                .andExpect(jsonPath("$[0].brand", Matchers.is(firstProduct.getBrand())))
                .andExpect(jsonPath("$[0].description", Matchers.is(firstProduct.getDescription())))
                .andExpect(jsonPath("$[0].image", Matchers.is(firstProduct.getImage())))
                .andExpect(jsonPath("$[0].price", Matchers.is(firstProduct.getPrice())))
                .andExpect(jsonPath("$[0].finalPrice", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].discount", Matchers.notNullValue()));
    }

    @Test
    void shouldReturnProductListByExactDescriptionWhenIsUppercase() throws Exception {
        mockMvc.perform(get("/products/search?filter=DESCRIPTION1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].description", Matchers.is(firstProduct.getDescription())));
    }

    @Test
    void shouldRetrieveNotFoundProductByFilter() throws Exception {
        mockMvc.perform(get("/products/search?filter=dummyBrand")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldFailMissingFilterParameter() throws Exception {
        mockMvc.perform(get("/products/search")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
