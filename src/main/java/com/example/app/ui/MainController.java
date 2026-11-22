package com.example.app.ui;

import com.example.app.AppContext;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

/**
 * Shell controller: owns the sidebar navigation and swaps the content area.
 */
public class MainController {

    @FXML
    private StackPane contentArea;

    @FXML
    private ToggleButton homeButton;

    @FXML
    private ToggleButton settingsButton;

    @FXML
    private ToggleGroup navGroup;

    private final AppContext context;

    public MainController(AppContext context) {
        this.context = context;
    }

    @FXML
    private void initialize() {
        // A ToggleGroup allows deselecting the active button; keep one selected.
        navGroup.selectedToggleProperty().addListener((obs, old, current) -> {
            if (current == null && old != null) {
                old.setSelected(true);
            }
        });
        showHome();
    }

    @FXML
    private void showHome() {
        homeButton.setSelected(true);
        setContent("home-view.fxml");
    }

    @FXML
    private void showSettings() {
        settingsButton.setSelected(true);
        setContent("settings-view.fxml");
    }

    private void setContent(String fxmlName) {
        contentArea.getChildren().setAll(Views.load(fxmlName, context));
    }
}
