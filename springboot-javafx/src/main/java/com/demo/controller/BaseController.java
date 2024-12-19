package com.demo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseController {

    private final String BASE_Path = "/static/views/";

    private StackPane contentArea; // 分頁顯示區域
    public void setContentPane(StackPane contentPane) {
        this.contentArea = contentPane;
    }

    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    // 分頁加載方法
    public void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource( BASE_Path + fxmlFile));
            Pane page = loader.load(); // 加載並取得根節點
            contentArea.getChildren().clear(); // 清除 現有的子節點。
            contentArea.getChildren().add(page); // 將page 節點 加到 contentArea 。
            if(loader.getController() instanceof BaseController) {  //檢查控制器類型
                ((BaseController) loader.getController()).setContentPane(contentArea);
            }
        } catch (IOException e) {
            System.err.println("-----Error loading FXML:----- " + fxmlFile);
            logger.error(e.getMessage(), e);
        }
    }

    private static final String LOCATION_PATH = "src/config/eGateLocation.json";
    public static String getLocation(){
        String location = "";
        try (
            InputStreamReader isr = new InputStreamReader(Files.newInputStream(Paths.get(LOCATION_PATH)),  StandardCharsets.UTF_8 )
        ) {

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(isr, JsonObject.class);
            location = jsonObject.get("eGateLocation").getAsString();

        } catch (IOException e) {
            System.err.println("-----Error getLocation()-----");
            logger.error(e.getMessage(), e);
        }
        return location;
    }

    // 按鈕切換
    @FXML
    public void handleSwitchPage(ActionEvent event) {
        Button button = (Button) event.getSource(); //按鈕對象
        String targetPage = button.getUserData().toString();
        loadView(targetPage);
    }
}
