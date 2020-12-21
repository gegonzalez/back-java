package com.example.application.service;

import com.example.application.controller.ProductResponse;
import com.example.application.controller.transformers.ProductResponseTransformer;
import com.example.application.domain.Product;
import com.example.application.repository.ProductMongoRepository;
import com.example.application.util.PromotionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final MongoTemplate mongoTemplate;
    private final ProductMongoRepository productMongoRepository;

    public ProductResponse find(final int id) {
        var foundProduct = productMongoRepository.findByIdQuery(id);
        return Optional.ofNullable(foundProduct).map(product -> getProductResponseWithDiscount(product))
                        .orElse(null);
    }

    public List<ProductResponse> find(final String filter) {
        Query orQuery = buildQueryToFilterByBrandOrDescription(filter);

        var productList = mongoTemplate.find(orQuery, Product.class);

        return productList.stream().map(product -> getProductResponseWithDiscount(product))
                                    .collect(Collectors.toList());
    }

    private ProductResponse getProductResponseWithDiscount(Product product) {
        return ProductResponseTransformer.from(product, PromotionUtil.calculateDiscount(product.getDescription(),
                                                                                        product.getPrice()));
    }

    private Query buildQueryToFilterByBrandOrDescription(final String filter) {
        List<String> fields = List.of("brand", "description");
        List<Criteria> orExpression = new ArrayList<>();
        for (String field : fields) {
            Criteria expression = new Criteria();
            expression.and(field).regex(filter, "i");
            orExpression.add(expression);
        }
        Query orQuery = new Query();
        Criteria orCriteria = new Criteria();

        return orQuery.addCriteria(orCriteria.orOperator(orExpression.toArray(new Criteria[fields.size()])));
    }
}
