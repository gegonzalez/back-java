package com.example.application.controller.transformers;

import com.example.application.domain.Discount;
import com.example.application.domain.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductResponseTransformerTest {
    private static final int ID = 1;
    private static final String BRAND = "brand";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final int PRICE = 100;
    public static final int FINAL_PRICE = 90;

    private final Product product = new Product(ID, BRAND, DESCRIPTION, IMAGE, PRICE);
    private final Discount discount = new Discount(10, 10);
    
    @Test
    void shouldBuildProductResponseFromProduct() {
        var response = ProductResponseTransformer.from(product, discount);

        assertNotNull(response);
        assertEquals(ID, response.getId());
        assertEquals(BRAND, response.getBrand());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(IMAGE, response.getImage());
        assertEquals(PRICE, response.getPrice());
        assertEquals(FINAL_PRICE, response.getFinalPrice());
        assertEquals(discount, response.getDiscount());
    }
}