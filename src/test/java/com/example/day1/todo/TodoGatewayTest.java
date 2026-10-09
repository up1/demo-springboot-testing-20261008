package com.example.day1.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoGatewayTest {

    @Autowired
    private TodoGateway todoGateway;

    @Test
    void case01() {
        // Act
        Optional<TodoResponse> result = todoGateway.getById(1);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), 1);
        assertEquals("delectus aut autem", result.get().getTitle());
    }
}