package com.demo;

import com.demo.controller.WebSocketClientViewController;
import com.demo.websocket.WebSocketClient;
import com.demo.websocket.WebSocketManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import static com.demo.util.ProgramCheckerUtil.isServerRunning;
import static com.demo.util.ProgramCheckerUtil.startWebsocketProjectServer;

public class MainApp extends Application {

	private ConfigurableApplicationContext springContext;
	private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

	@Override
	public void init() throws Exception {
		springContext = new SpringApplicationBuilder(Main.class).run();
	}

	@Override
	public void start(Stage stage) throws Exception {
		// 1.1 檢查 server專案的程式是否啟動 未啟動 則嘗試啟動server端
		if (!isServerRunning("WebsocketApp") && !startWebsocketProjectServer()) {
			return;
		}
		logger.info("server端已啟動");

		// 1.2 server程式 已啟動 顯示主畫面
		showMainView(stage);
		logger.info("client端已啟動");
	}

	private void showMainView(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/static/views/mainView.fxml"));

//		Object controller = fxmlLoader.getController();
//		WebSocketClientViewController wsController = (WebSocketClientViewController) controller;
//		WebSocketManager.getInstance().initialize(wsController);

		Scene scene = new Scene(fxmlLoader.load(), 600, 600);
		stage.setTitle("MNR查詢系統");
		stage.setScene(scene);
		stage.show();

	}

	@Override
	public void stop() throws Exception {
		super.stop();
		springContext.close(); 	// 關閉 Spring 上下文
	}

	public static void main(String[] args) {
		launch(); // 啟動 JavaFX
	}

}