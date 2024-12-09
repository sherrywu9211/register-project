package com.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class JavaFxApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Label label = new Label("Hello, Spring Boot and JavaFX!");
//        Scene scene = new Scene(label, 400, 300);
//        stage.setScene(scene);
//        stage.setTitle("JavaFX Application");
//        stage.show();

        // 放FXML路徑
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/static/views/post-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }
}
