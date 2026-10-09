package com.example.day1.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class ProductControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ProductRepository productRepository;

    @AfterEach
    public void clearData() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Success case with get product by id = 1")
    void case01() {
        // Arrange
        Product product = new Product("Product name 01", 100.50);
        productRepository.save(product);

        // Act
        ProductResponse result = restTemplate.getForObject("/api/product/1", ProductResponse.class);

        // Assert
        assertEquals(1, result.getId());
        assertEquals("Product name 01", result.getName());
    }
}