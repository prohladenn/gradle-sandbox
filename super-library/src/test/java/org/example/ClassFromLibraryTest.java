package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassFromLibraryTest {

    @Test
    void getValue() {
        ClassFromLibrary classFromLibrary = new ClassFromLibrary(42);
        assertEquals(42, classFromLibrary.value(), "The value should be 42");
    }
}