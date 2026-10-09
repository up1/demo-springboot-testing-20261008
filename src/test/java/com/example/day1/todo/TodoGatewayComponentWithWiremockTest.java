package com.example.day1.todo;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}