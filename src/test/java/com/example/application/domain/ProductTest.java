package com.example.application.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ProductTest {
    private static final int ID = 1;
    private static final String BRAND = "brand";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final int PRICE = 100;

    Product product = new Product(ID, BRAND, DESCRIPTION, IMAGE, PRICE);

    @Test
    void shouldGetProductCorrectly() {
        assertThat(product.getId(), is(ID));
        assertThat(product.getBrand(), is(BRAND));
        assertThat(product.getDescription(), is(DESCRIPTION));
        assertThat(product.getImage(), is(IMAGE));
        assertThat(product.getPrice(), is(PRICE));
    }
}