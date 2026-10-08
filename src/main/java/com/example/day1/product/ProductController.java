package com.example.day1.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/api/product/{id}")
    public ProductResponse getById(@PathVariable int id) {
        return new ProductResponse();
    }

}
