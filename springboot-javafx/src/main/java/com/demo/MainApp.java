package com.demo;

import com.demo.controller.WebSocketClientController;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.WebSocketContainer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URI;

import static com.demo.util.ProgramCheckerUtil.isProgramRunning;

public class MainApp extends Application {

	private ConfigurableApplicationContext springContext;

	@Override
	public void init() throws Exception {
		springContext = new SpringApplicationBuilder(Main.class).run();
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO
		// 1. 檢查 B專案的程式是否啟動
//		if(isProgramRunning("IntelliJ IDEA")) {
			// 1.2 若啟動 再檢查WebSocket 是否連線成功
			try {
				WebSocketContainer container = ContainerProvider.getWebSocketContainer();
				container.connectToServer(WebSocketClientController.class, URI.create("ws://localhost:8081/ws"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 2.1 若連線失敗 重複五次 若失敗 再顯示錯誤畫面

			// 2.2 若連線成功 執行FXML
			// 使用 Spring 管理的 FXMLLoader
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/static/views/main.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 600, 600);
			stage.setTitle("MNR查詢系統");
			stage.setScene(scene);
			stage.show();

//		}else {
//			// 1.1 若未啟動 顯示錯誤網頁
//			System.out.println("B專案未啟動");
//		}
	}


	// 檢查WebSocket



	@Override
	public void stop() throws Exception {
		springContext.close(); 	// 關閉 Spring 上下文
	}

	public static void main(String[] args) {
		launch(); // 啟動 JavaFX
	}

}