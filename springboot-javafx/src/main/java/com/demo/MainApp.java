package com.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApp extends Application{
	private ConfigurableApplicationContext springContext;

	@Override
	public void init() throws Exception {
		// 啟動 Spring 應用程序上下文
		springContext = new SpringApplicationBuilder(MainApp.class).run();
	}

	@Override
	public void start(Stage stage) throws Exception {
		// 使用 Spring 管理的 FXMLLoader
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/static/views/main.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 600, 600);
			stage.setTitle("MNR查詢系統");
			stage.setScene(scene);
			stage.show();
	}

	@Override
	public void stop() throws Exception {
		springContext.close(); 	// 關閉 Spring 上下文
	}

	public static void main(String[] args) {
		launch(); // 啟動 JavaFX
	}

}
