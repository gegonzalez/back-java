package com.example.application.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "products")
public class Product {

    @MongoId
    private String _id;

    private int id;

    private String brand;

    private String description;

    private String image;

    private int price;

    public Product() {
    }

    public Product(int id, String brand, String description, String image, int price) {
        this.id = id;
        this.brand = brand;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }
}
