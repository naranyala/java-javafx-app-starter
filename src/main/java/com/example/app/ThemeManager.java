package com.example.app;

import javafx.scene.Parent;

/**
 * Applies a {@link Theme} to a live scene by toggling a style class. The actual
 * colors live in {@code styles.css} as looked-up color variables.
 */
public final class ThemeManager {

    private static final String DARK_CLASS = "dark";

    private ThemeManager() {
    }

    public static void apply(Parent root, Theme theme) {
        root.getStyleClass().remove(DARK_CLASS);
        if (theme == Theme.DARK) {
            root.getStyleClass().add(DARK_CLASS);
        }
    }
}
