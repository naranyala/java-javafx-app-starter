package com.example.app.ui;

import com.example.app.AppContext;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Demo view controller. Shows the pattern for constructor injection and FXML
 * event handlers.
 */
public class HomeController {

    @FXML
    private TextField nameField;

    @FXML
    private Label greetingLabel;

    private final AppContext context;

    public HomeController(AppContext context) {
        this.context = context;
    }

    @FXML
    private void initialize() {
        greetingLabel.setText(context.greeter().greet(""));
    }

    @FXML
    private void onGreet() {
        greetingLabel.setText(context.greeter().greet(nameField.getText()));
    }
}
