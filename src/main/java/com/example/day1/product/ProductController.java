package com.example.day1.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/api/product/{id}")
    public ProductResponse getById(@PathVariable int id) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(id);
        productResponse.setName("Product name 01");
        return productResponse;
    }

}
