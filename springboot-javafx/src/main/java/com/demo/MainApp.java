package com.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		// 放FXML路徑
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/static/views/main.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 500, 600);
		stage.setTitle("MNR查詢系統");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(); // 啟動 JavaFX
	}

}
