package com.demo.util;

import com.demo.model.Resident;
import com.demo.service.EmergencyRespServiceImpl;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
//            throw new RuntimeException(e);
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
    public String changEmergencyApi(String switchSystem, String switchLocation){
        String emergency_Url = UriComponentsBuilder.fromHttpUrl(MonToEnrEmergency_URL)
                .queryParam("switchSystem", switchSystem)
                .queryParam("switchLocation", switchLocation)
                .toUriString();
        String result = restTemplate.getForObject(emergency_Url, String.class);
        logger.info("--API 3 Request url-- : " + emergency_Url);
        logger.info("--API 3 Response-- : " + result);
        return result;
    }

}