package com.example.application.repository;

import com.example.application.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMongoRepository extends MongoRepository<Product, String> {
    @Query("{id : ?0}")
    Product findByIdQuery(int id);
}
