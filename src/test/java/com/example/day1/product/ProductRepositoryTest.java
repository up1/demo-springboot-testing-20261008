package com.example.day1.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Success case with id=1")
    void case01(){
        // Arrange
        Product product = new Product("Demo", 100.50);
        productRepository.save(product);

        // Act
        Optional<Product> result = productRepository.findById(1);

        // Assert
        assertTrue(result.isPresent());
    }

}