package com.example.app;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Small composition root shared by controllers.
 *
 * <p>Dependencies are passed explicitly through this object instead of relying
 * on a DI framework. Add new services here as the app grows.
 */
public final class AppContext {

    private final AppPreferences preferences;
    private final Greeter greeter = new Greeter();
    private Consumer<Theme> themeApplier = theme -> { };

    public AppContext(AppPreferences preferences) {
        this.preferences = Objects.requireNonNull(preferences, "preferences");
    }

    public AppPreferences preferences() {
        return preferences;
    }

    public Greeter greeter() {
        return greeter;
    }

    /**
     * Registers the callback used to restyle the live scene when the theme
     * changes. Set once by {@link MainApp} during startup.
     */
    public void onThemeChanged(Consumer<Theme> applier) {
        this.themeApplier = Objects.requireNonNull(applier, "applier");
    }

    public void applyTheme(Theme theme) {
        preferences.setTheme(theme);
        themeApplier.accept(theme);
    }
}
