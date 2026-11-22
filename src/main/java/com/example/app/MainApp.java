package com.example.app;

import java.io.IOException;
import java.util.Objects;

import com.example.app.ui.Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX application entry point.
 *
 * <p>Creates the shared {@link AppContext}, loads the FXML shell, wires the
 * theme callback, and shows the primary stage.
 */
public class MainApp extends Application {

    private static final String SHELL = "/com/example/app/main-view.fxml";
    private static final String STYLES = "/com/example/app/styles.css";

    @Override
    public void start(Stage stage) throws IOException {
        AppContext context = new AppContext(new AppPreferences());

        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(SHELL));
        loader.setControllerFactory(Views.controllerFactory(context));
        Parent root = loader.load();

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(
                Objects.requireNonNull(MainApp.class.getResource(STYLES), "Missing " + STYLES)
                        .toExternalForm());

        ThemeManager.apply(root, context.preferences().theme());
        context.onThemeChanged(theme -> ThemeManager.apply(root, theme));

        stage.setTitle("JavaFX Starter");
        stage.setMinWidth(720);
        stage.setMinHeight(480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
