package com.example.day1.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class ProductControllerFailureTest {

    @Autowired
    TestRestTemplate restTemplate;

    @MockitoBean
    ProductRepository productRepository;

    @Test
    @DisplayName("Product not found with id=2")
    void case02() {
        // Arrange
        when(productRepository.findById(2))
                .thenReturn(Optional.empty());

        // Act
        ResponseEntity<ErrorMessageResponse> result = restTemplate.getForEntity("/api/product/2", ErrorMessageResponse.class);

        // Assert ... 404
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Product not found in system", result.getBody().getMessage());
    }

    @Test
    @DisplayName("Success case with get product by id = 1")
    void case01() {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("Product name 01");
        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        // Act
        ProductResponse result = restTemplate.getForObject("/api/product/1", ProductResponse.class);

        // Assert
        assertEquals(1, result.getId());
        assertEquals("Product name 01", result.getName());

        verify(productRepository, times(1)).findById(1);
    }
}