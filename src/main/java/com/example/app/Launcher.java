package com.example.app;

/**
 * Entry point for the application.
 *
 * <p>JavaFX refuses to start when the main class itself extends
 * {@link javafx.application.Application} and the JavaFX runtime is on the
 * classpath. Launching from this plain class avoids that restriction and keeps
 * the setup non-modular (no {@code module-info.java} needed).
 */
public final class Launcher {

    private Launcher() {
    }

    public static void main(String[] args) {
        MainApp.main(args);
    }
}
