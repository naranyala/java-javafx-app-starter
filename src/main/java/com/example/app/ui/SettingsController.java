package com.example.app.ui;

import com.example.app.AppContext;
import com.example.app.Theme;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

/**
 * Settings view controller. Changing the theme updates the live scene and
 * persists the choice for the next launch.
 */
public class SettingsController {

    @FXML
    private ComboBox<Theme> themeBox;

    private final AppContext context;

    public SettingsController(AppContext context) {
        this.context = context;
    }

    @FXML
    private void initialize() {
        themeBox.getItems().setAll(Theme.values());
        themeBox.setValue(context.preferences().theme());
        themeBox.valueProperty().addListener((obs, old, value) -> {
            if (value != null) {
                context.applyTheme(value);
            }
        });
    }
}
