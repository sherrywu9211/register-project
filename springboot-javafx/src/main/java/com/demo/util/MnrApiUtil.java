package com.demo.util;

import com.demo.model.Resident;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MnrApiUtil {

    // MNR註冊查詢
    private static final String MNR4_BACKEND_URL = "http://10.11.10.100:10000/MNR4_Backend/api/mnr/QueryEgateApplication";

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    public String MnrApi(Resident resident) {

        // 物件轉JSON
        String mnrParams;
        try {
            mnrParams = objectMapper.writeValueAsString(resident);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("-------mnrParams--------"+ mnrParams);

        // 設定API headers &　參數
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mnrParams, headers);
        // API 傳送參數 & 回傳參數
        ResponseEntity<String> response = restTemplate.postForEntity(
                MNR4_BACKEND_URL, entity, String.class
        );

        // 取得JSON response
        if(response.getStatusCode().is2xxSuccessful()) {
            System.out.println( "-----response------" + response.getBody());
            String respBody = response.getBody();

            return respBody;
        }else {
            System.out.println( "-----error------" + response.getBody());
            return null;
        }
    }

}
