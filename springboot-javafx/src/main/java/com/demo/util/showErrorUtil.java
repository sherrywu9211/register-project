package com.demo.util;

import javafx.scene.control.Alert;

public class showErrorUtil {

    public static void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("錯誤");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
