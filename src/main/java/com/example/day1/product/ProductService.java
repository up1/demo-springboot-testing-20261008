package com.example.day1.product;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse get(int id) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(id);
            productResponse.setName(result.get().getName());
            return productResponse;
        }
        throw new RuntimeException("Product not found");
    }

}
