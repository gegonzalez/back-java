package com.example.application.controller.transformers;

import com.example.application.controller.ProductResponse;
import com.example.application.domain.Discount;
import com.example.application.domain.Product;

public class ProductResponseTransformer {
    public static ProductResponse from(final Product product, final Discount discount) {
        return ProductResponse.builder()
                .id(product.getId())
                .brand(product.getBrand())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .finalPrice(product.getPrice() - discount.getAmount())
                .discount(discount)
                .build();
    }
}
