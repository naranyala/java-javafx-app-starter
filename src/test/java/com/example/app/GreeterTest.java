package com.example.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GreeterTest {

    @Test
    void greetsByName() {
        assertEquals("Hello, Ada!", Greeter.greet("Ada"));
    }

    @Test
    void trimsWhitespace() {
        assertEquals("Hello, Ada!", Greeter.greet("  Ada  "));
    }

    @Test
    void fallsBackWhenBlank() {
        assertEquals("Hello, JavaFX!", Greeter.greet("   "));
    }

    @Test
    void handlesNull() {
        assertEquals("Hello, JavaFX!", Greeter.greet(null));
    }
}
