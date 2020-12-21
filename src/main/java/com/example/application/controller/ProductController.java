package com.example.application.controller;

import com.example.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/products")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<ProductResponse>> search(@RequestParam @NotBlank String filter) {
        LOG.info(String.format("Search product filter=%s",  filter));

        var productListResponse = productService.find(filter);
        if (productListResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(productListResponse);
    }

    @GetMapping(path="/{id}")
    @ResponseBody
    public ResponseEntity<ProductResponse> find(@PathVariable("id") int id) {
        LOG.info(String.format("Get product Id=%s", id));

        return Optional.ofNullable(productService.find(id))
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
