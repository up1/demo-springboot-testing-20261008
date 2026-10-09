package com.example.day1.todo;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoGatewayComponentWithWiremockTest {

    @RegisterExtension
    static WireMockExtension todoApi = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    @DynamicPropertySource
    static void todoApiProperties(DynamicPropertyRegistry registry) {
        registry.add("base_url", todoApi::baseUrl);
    }

    @Autowired
    private TodoGateway todoGateway;

    @Test
    void case01() {
        // Arrange
        todoApi.stubFor(get("/todos/1")
                .willReturn(okJson("""
                    {
                      "userId": 1,
                      "id": 1,
                      "title": "Mock title from wiremock",
                      "completed": false
                    }
                    """).withFixedDelay(5000)));

        // Act
        Optional<TodoResponse> result = todoGateway.getById(1);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), 1);
        assertEquals("Mock title from wiremock", result.get().getTitle());
    }

    @Test
    @DisplayName("Fail case with todo api return internal server error (500)")
    void case02() {
        // Arrange
        todoApi.stubFor(get("/todos/1")
                .willReturn(serverError()));

        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            todoGateway.getById(1);
        });
        assertEquals("Todo API error with status 500", exception.getMessage());
    }
}