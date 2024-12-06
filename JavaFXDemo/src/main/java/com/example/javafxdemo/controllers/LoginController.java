package com.example.javafxdemo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label loginButton;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("controller print text!");
        loginButton.setText("登入頁還沒做完 再等等~");
    }
}