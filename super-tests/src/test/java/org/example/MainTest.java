package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void testClassFromService() {
        ClassFromService classFromService = new ClassFromService(42);
        assertEquals(42, classFromService.getValue(), "The value should be 42");
    }
}