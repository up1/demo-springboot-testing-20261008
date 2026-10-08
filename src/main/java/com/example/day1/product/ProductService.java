package com.example.day1.product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ProductResponse get(int id) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(id);
        productResponse.setName("Product name 01");
        return productResponse;
    }

}
