package com.demo.util;

import com.demo.model.EmergencyRequest;
import com.demo.model.Resident;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MnrApiUtil {

    public static final Logger logger = LoggerFactory.getLogger(MnrApiUtil.class);

    RestTemplate restTemplate = new RestTemplate();

    // MNR註冊查詢
    private static final String MNR4_BACKEND_URL = "http://10.11.10.100:10000/MNR4_Backend/api/mnr/QueryEgateApplication";
    public String mnrApi(Resident resident) {
        // 物件轉JSON
        String mnrParams= "";
        try {
            Gson gson = new Gson();
            mnrParams = gson.toJson(resident);
        } catch (Exception e) {
            logger.error("-----error------" + e.getMessage(), e);
        }

        // 設定API headers &　參數
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mnrParams, headers);
        // API 傳送參數 & 回傳參數
        ResponseEntity<String> response = restTemplate.postForEntity(
                MNR4_BACKEND_URL, entity, String.class
        );
        logger.info("--API 1 Request-- : " + entity);
        logger.info("--API 1 Response-- : " + response);
        // 取得JSON response
        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }else {
            logger.error("-----error------" + response.getBody());
            return null;
        }
    }

    // 第二支API
    private static final String MNR4_PERSON_URL = "http://10.11.10.100:10000/MNR4_Backend/api/mnr/QueryEgateApplicationDetail";
    public String oneMnrApi(String travelId, String passportNo, String seqNo){
        String personUrl = UriComponentsBuilder.fromHttpUrl(MNR4_PERSON_URL)
                .queryParam("travelId", travelId)
                .queryParam("seqNo", seqNo)
                .queryParam("passportNo", passportNo)
                .toUriString();
        String result = restTemplate.getForObject(personUrl, String.class);
        logger.info("--API 2 Request-- : " + personUrl);
        logger.info("--API 2 Response-- : " + result);
        return result;
    }

    // 第三支API
    private static final String MonToEnrEmergency_URL = "http://localhost:8080/api/MonToEnrEmergency";
    public String changEmergencyApi(EmergencyRequest emergencyRequest){

        // 物件轉JSON
        String mnrParams= "";
        try {
            Gson gson = new Gson();
            mnrParams = gson.toJson(emergencyRequest);
        } catch (Exception e) {
            logger.error("-----error------" + e.getMessage(), e);
        }
        // 設定API headers &　參數
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mnrParams, headers);
        // API 傳送參數 & 回傳參數
        ResponseEntity<String> response = restTemplate.postForEntity(
                MonToEnrEmergency_URL, entity, String.class
        );
        logger.info("--API 3 Request-- : " + entity);
        logger.info("--API 3 Response-- : " + response);

        // 儲存為JSON檔
        saveEmergencyRecord(mnrParams, "request_");
        saveEmergencyRecord(response.getBody() , "response_");

        // 取得JSON response
        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }else {
            logger.error("-----error------" + response.getBody());
            return null;
        }
    }

    // 存入第三支API的JSON檔
    private static final String EMERGENCY_RECORD_URL = "emergency_api_record";
    public static void saveEmergencyRecord(String jsonParams, String reqOrResp){
        // 取日期
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = now.format(formatter);

        // 設定匯出檔案
        String file = reqOrResp + timestamp + ".json";
        Path path = Paths.get(EMERGENCY_RECORD_URL);
        Path filePath = path.resolve(file);

        try {
            // 確保目錄存在
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