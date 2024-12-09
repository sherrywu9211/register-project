package com.demo.util;

import com.demo.model.Resident;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MnrApiUtil {

    // MNR註冊查詢
    private static final String MNR4_BACKEND_URL = "http://10.11.10.100:10000/MNR4_Backend/api/mnr/QueryEgateApplication";

    @Autowired
    private RestTemplate restTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    // todo
    public String MnrApi(Resident resident) {

        // 物件轉JSON
        String mnrParams;
        try {
            mnrParams = objectMapper.writeValueAsString(resident);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(mnrParams);

        // 傳送參數API
        // 設定傳送表頭格式與請求參數
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<>(mnrParams, headers);

        // API 傳送參數 & 回傳參數
        ResponseEntity<String> response = restTemplate.postForEntity(
                MNR4_BACKEND_URL, mnrParams, String.class
        );

        // 取得JSON response
        if(response.getStatusCode().is2xxSuccessful()) {
            System.out.println( "-----response------" + response.getBody());
            return response.getBody();
        }else {
            System.out.println( "-----error------" + response.getBody());
            return null;
        }
//        return "test-----";
    }




}
