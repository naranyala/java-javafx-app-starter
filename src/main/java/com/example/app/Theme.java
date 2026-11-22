package com.example.app;

/**
 * Available UI themes. The {@link #key()} is persisted in
 * {@link AppPreferences}; the CSS class is applied to the scene root by
 * {@link ThemeManager}.
 */
public enum Theme {

    LIGHT("Light", "light"),
    DARK("Dark", "dark");

    private final String displayName;
    private final String key;

    Theme(String displayName, String key) {
        this.displayName = displayName;
        this.key = key;
    }

    public String key() {
        return key;
    }

    public String displayName() {
        return displayName;
    }

    public static Theme fromKey(String key) {
        for (Theme theme : values()) {
            if (theme.key.equals(key)) {
                return theme;
            }
        }
        return LIGHT;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
