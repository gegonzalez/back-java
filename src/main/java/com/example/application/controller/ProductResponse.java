package com.example.application.controller;

import com.example.application.domain.Discount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ProductResponse {
    private int id;

    private String brand;

    private String description;

    private String image;

    private int price;

    private int finalPrice;

    private Discount discount;
}
