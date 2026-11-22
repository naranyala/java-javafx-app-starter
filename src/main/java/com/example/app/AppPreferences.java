package com.example.app;

import java.util.Objects;
import java.util.prefs.Preferences;

/**
 * Typed wrapper around {@link Preferences}.
 *
 * <p>Uses the JDK's built-in preference store, so the starter needs no extra
 * dependency. On Linux this is backed by {@code ~/.java/.userPrefs}.
 */
public final class AppPreferences {

    private static final String KEY_THEME = "ui.theme";

    private final Preferences node;

    public AppPreferences() {
        this(Preferences.userNodeForPackage(AppPreferences.class));
    }

    AppPreferences(Preferences node) {
        this.node = Objects.requireNonNull(node, "node");
    }

    public Theme theme() {
        return Theme.fromKey(node.get(KEY_THEME, Theme.LIGHT.key()));
    }

    public void setTheme(Theme theme) {
        node.put(KEY_THEME, Objects.requireNonNull(theme, "theme").key());
    }
}
