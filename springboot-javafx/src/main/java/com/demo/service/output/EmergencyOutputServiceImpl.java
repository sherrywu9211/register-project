package com.demo.service.output;

import com.demo.model.EmergencyOutputEntity;
import com.demo.model.EmergencyInputEntity;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmergencyOutputServiceImpl implements EmergencyOutputService {


    public static final Logger logger = LoggerFactory.getLogger(EmergencyOutputServiceImpl.class);
    private static final String EMERGENCY_URL = "http://localhost:8080/api/MonToEnrEmergency";
    RestTemplate restTemplate = new RestTemplate();
    Gson gson = new Gson();

    // 第三支API Emergency
    @Override
    public EmergencyInputEntity sendEmergencyRequest(String switchSystem, String switchLocation) {

        EmergencyOutputEntity emergencyOutputEntity = new EmergencyOutputEntity();
        emergencyOutputEntity.setSwitchSystem(switchSystem);
        emergencyOutputEntity.setSwitchLocation(switchLocation);

        // 物件轉JSON
        String mnrParams= "";
        try {
            Gson gson = new Gson();
            mnrParams = gson.toJson(emergencyOutputEntity);
        } catch (Exception e) {
            logger.error("--API 3 -error---- : " + e.getMessage(), e);
        }
        // 設定API headers &　參數
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mnrParams, headers);

        try {
            // API 傳送參數 & 回傳參數
            ResponseEntity<String> response = restTemplate.postForEntity(
                    EMERGENCY_URL, entity, String.class
            );

            // 儲存LOG檔
            logger.info("--API 3 Request-- : " + entity);
            logger.info("--API 3 Response-- : " + response);

            // 儲存為JSON檔
            saveEmergencyRecord("request_" , mnrParams);
            saveEmergencyRecord("response_" , response.getBody());

            return gson.fromJson(response.getBody(), EmergencyInputEntity.class);
        } catch (Exception e) {
            logger.error("--API 3 -error---- : " + e.getMessage(), e);
            return null;
        }
    }

    // 存入第三支API的JSON檔
    private static final String EMERGENCY_RECORD_URL = "emergency_api_record";
    public static void saveEmergencyRecord(String reqOrResp, String jsonParams){
        // 取日期
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = now.format(formatter);

        // 設定匯出檔案
        String file = reqOrResp + timestamp + ".json";
        Path path = Paths.get(EMERGENCY_RECORD_URL);
        Path filePath = path.resolve(file);

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            // 將 JSON 資料寫入檔案
            Files.write(filePath, jsonParams.getBytes());
        } catch (IOException e) {
            logger.error("-----error------" + e.getMessage());
        }
    }
}