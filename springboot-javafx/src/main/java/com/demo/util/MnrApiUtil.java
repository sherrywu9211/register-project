package com.demo.util;

import com.demo.model.Resident;
import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MnrApiUtil {

    // MNR註冊查詢
    private static final String MNR4_BACKEND_URL = "http://10.11.10.100:10000/MNR4_Backend/api/mnr/QueryEgateApplication";
    RestTemplate restTemplate = new RestTemplate();

<<<<<<< HEAD
    public String MnrApi(Resident resident) {
=======
    public String mnrApi(Resident resident) {
>>>>>>> API2

        // 物件轉JSON
        String mnrParams;
        try {
            Gson gson = new Gson();
            mnrParams = gson.toJson(resident);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
            return response.getBody();
        }else {
            System.out.println( "-----error------" + response.getBody());
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

        return restTemplate.getForObject(personUrl, String.class);
    }


}
