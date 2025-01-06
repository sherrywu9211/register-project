package com.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import static com.demo.util.ProgramCheckerUtil.isProgramRunning;
import static javax.management.remote.JMXConnectorFactory.connect;

public class MainApp extends Application {

	private ConfigurableApplicationContext springContext;
	private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

	@Override
	public void init() throws Exception {
		springContext = new SpringApplicationBuilder(Main.class).run();
	}

	@Override
	public void start(Stage stage) throws Exception {
		// 1. 檢查 server專案的程式是否啟動
		if (!isProgramRunning("WebsocketApp")) {
			// 1.1 如果未啟動 則嘗試啟動server端
			logger.warn("server端未啟動，client端 嘗試啟動server端");
			try {
				Process process = Runtime.getRuntime().exec("C:/workspace/websocket-project/target/websocket-project/websocket-project.exe");
				process.waitFor();
			}catch (Exception e) {
				// 嘗試啟動失敗 顯示錯誤畫面
				logger.error("server端程式 啟動失敗" + e);
				showErrorAlert(stage, "server端程式 啟動失敗，請稍後再試");
				return;
			}
		}
		// 1.2 server程式已啟動
		logger.info("server端已啟動");
		// 2. 檢查websSocket連線
		checkConnection(stage);
		// 2.2 若連線成功 執行FXML
		showMainView(stage);
	}

	private int retryCount = 0;
	private void checkConnection(Stage stage) {
		retryCount++;
		logger.info("WebSocket 連線，第 " + retryCount + " 次嘗試連線");

		if (retryCount >= 5) {
			// 2.1 若連線失敗 重複五次，若失敗 顯示錯誤畫面
			Platform.runLater(() -> showErrorAlert(stage, "WebSocket連線失敗，請稍後再試。"));
			logger.error("WebSocket連線5次失敗");
		} else {
			try {
				Thread.sleep(2000); // 等待 2 秒後重試
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}


	private void showMainView(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/static/views/mainView.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 600, 600);
		stage.setTitle("MNR查詢系統");
		stage.setScene(scene);
		stage.show();
	}

	private void showErrorAlert(Stage stage, String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("發生錯誤");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
		Platform.exit(); // 結束應用程式
	}

	@Override
	public void stop() throws Exception {
		springContext.close(); 	// 關閉 Spring 上下文
	}

	public static void main(String[] args) {
		launch(); // 啟動 JavaFX
	}

}