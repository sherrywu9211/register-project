package com.demo.util;

import com.demo.controller.BaseController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GateLocationUtil {

    public static final Logger logger = LoggerFactory.getLogger(GateLocationUtil.class);
    private static final Gson gson = new Gson();
    private static final String LOCATION_PATH = "src/config/eGateLocation.json";

    public String changeLocation(String location) {
        switch (location){
            case "00-雲端正式":
            case "01-桃園機場第一航廈":
            case "02-高雄小港機場":
            case "11-台北松山機場":
            case "12-桃園機場第二航廈":
            case "13-金門港":
                return location;
            default:
                return "error";
        }

    };

    // 取得設定檔的值
    public static String getLocation(){
        String location = "";
        try (
            InputStreamReader isr = new InputStreamReader(Files.newInputStream(Paths.get(LOCATION_PATH)),  StandardCharsets.UTF_8 )
        ) {
            JsonObject jsonObject = gson.fromJson(isr, JsonObject.class);
            location = jsonObject.get("eGateLocation").getAsString();
        } catch (IOException e) {
            System.err.println("-----Error getLocation()-----");
            logger.error(e.getMessage(), e);
        }
        return location;
    }

    // 更新設定檔的值
    public static void setLocation(String newLocation) {
        try{
            // 讀取現有 JSON 檔案
            JsonObject jsonObject = gson.fromJson(Files.readString(Paths.get(LOCATION_PATH), StandardCharsets.UTF_8), JsonObject.class);

            // 更新 eGateLocation 值
            jsonObject.addProperty("eGateLocation", newLocation);

            // 將更新後的內容寫回檔案
            Files.writeString(Paths.get(LOCATION_PATH), gson.toJson(jsonObject), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("-----Error setLocation()-----");
            logger.error(e.getMessage(), e);
        }
    }



}
