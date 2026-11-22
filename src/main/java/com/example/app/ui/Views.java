package com.example.app.ui;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;

import com.example.app.AppContext;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;

/**
 * Loads FXML views and constructs their controllers with dependencies from the
 * shared {@link AppContext}.
 *
 * <p>Keep the mapping in {@link #controllerFactory(AppContext)} up to date when
 * you add a new controller.
 */
public final class Views {

    private static final String BASE = "/com/example/app/";

    private Views() {
    }

    public static Parent load(String fxmlName, AppContext context) {
        URL resource = Views.class.getResource(BASE + fxmlName);
        if (resource == null) {
            throw new IllegalArgumentException("Missing FXML resource: " + BASE + fxmlName);
        }

        FXMLLoader loader = new FXMLLoader(resource);
        loader.setControllerFactory(controllerFactory(context));
        try {
            return loader.load();
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to load " + fxmlName, e);
        }
    }

    public static Callback<Class<?>, Object> controllerFactory(AppContext context) {
        return type -> {
            if (type == MainController.class) {
                return new MainController(context);
            }
            if (type == HomeController.class) {
                return new HomeController(context);
            }
            if (type == SettingsController.class) {
                return new SettingsController(context);
            }
            throw new IllegalStateException("No controller registered for " + type.getName());
        };
    }
}
