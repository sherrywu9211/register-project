package com.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public abstract class BaseController {
    private static final String MNR_REGISTER_VIEW = "/static/views/mnrRegister.fxml";
    private static final String API_CHANGE_VIEW = "/static/views/apiChange.fxml";
    private static final String MNR_PERSON = "/static/views/mnrPerson.fxml";

    protected StackPane contentArea;
    public void setContentPane(StackPane contentPane) {
        this.contentArea = contentPane;
    }
    private int pagePointer;
    private final String baseLocation = "/static/views/";

    public void loadPage(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(baseLocation + fxmlFile));
            Pane page = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(page);
            if (loader.getController() instanceof BaseController) {
                ((BaseController) loader.getController()).setContentPane(contentArea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSwitchPage(ActionEvent event) {
        // 專門給按鈕用的
        Button clickedButton = (Button) event.getSource();
        String targetPage = (String) clickedButton.getUserData();
        System.out.println("切換到：" + targetPage);
        loadPage(targetPage);
    }
}
