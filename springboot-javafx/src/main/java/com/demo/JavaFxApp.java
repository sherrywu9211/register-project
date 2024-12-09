package com.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class JavaFxApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Hello, Spring Boot and JavaFX!");
        Scene scene = new Scene(label, 400, 300);
        stage.setScene(scene);
        stage.setTitle("JavaFX Application");
        stage.show();
    }
}
