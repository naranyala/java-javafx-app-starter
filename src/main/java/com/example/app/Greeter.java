package com.example.app;

/**
 * GUI-free greeting logic, kept separate so it can be unit tested without
 * starting the JavaFX toolkit.
 */
public final class Greeter {

    public String greet(String name) {
        String trimmed = name == null ? "" : name.strip();
        return trimmed.isEmpty() ? "Hello, JavaFX!" : "Hello, " + trimmed + "!";
    }
}
