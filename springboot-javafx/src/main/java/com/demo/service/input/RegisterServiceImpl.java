package com.demo.service.input;

import com.demo.model.RegisterListEntity;
import com.demo.model.RegisterPersonEntity;
import com.demo.model.RegisterSelectEntity;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RegisterServiceImpl implements RegisterService {

    public static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);
    private static final String MNR4_BACKEND_URL = "http://10.11.10.100:10000/MNR4_Backend/api/mnr/QueryEgateApplication";
    private static final String MNR4_PERSON_URL = "http://10.11.10.100:10000/MNR4_Backend/api/mnr/QueryEgateApplicationDetail";
    Gson gson = new Gson();
    RestTemplate restTemplate = new RestTemplate();

    // 第一支API MNR註冊查詢
    @Override
    public List<RegisterListEntity> selectList(RegisterSelectEntity resident) {

        // 物件轉JSON
        String mnrParams= "";
        try {
            Gson gson = new Gson();
            mnrParams = gson.toJson(resident);
        } catch (Exception e) {
            logger.error("-----error------" + e.getMessage(), e);
        }

        // 設定API headers &　JSON參數
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mnrParams, headers);

        ResponseEntity<String> response = null;
        // 取得JSON response
        try{
            // API 傳送參數 & 回傳參數
            response = restTemplate.postForEntity(
                    MNR4_BACKEND_URL, entity, String.class
            );
            // 發送與回應API的內容 存LOG
            logger.info("--API 1 Request-- : " + entity);
            logger.info("--API 1 Response-- : " + response);

            String jsonData = response.getBody();
            // 定義泛型類型
            Type listType = new TypeToken<List<RegisterListEntity>>() {}.getType();
            // 轉回物件
            String resListJson = gson.fromJson(jsonData, JsonObject.class).get("resList").toString();
            return gson.fromJson(resListJson, listType);
        }catch (Exception e) {
            logger.error("--API 1 error-- : " + e);
            return null;
        }
    }

    // 第二支API 個人查詢
    @Override
    public RegisterPersonEntity selectOnePerson(String travelId, String passportNo, String seqNo) {

        try {
            // 呼叫API & 取得回應結果
            String personUrl = UriComponentsBuilder.fromHttpUrl(MNR4_PERSON_URL)
                    .queryParam("travelId", travelId)
                    .queryParam("seqNo", seqNo)
                    .queryParam("passportNo", passportNo)
                    .toUriString();
            String responseJson = restTemplate.getForObject(personUrl, String.class);

            logger.info("--API 2 Request-- : " + personUrl);
            logger.info("--API 2 Response-- : " + responseJson);
            return gson.fromJson(responseJson, RegisterPersonEntity.class);
        } catch (RuntimeException e) {
            logger.error("--API 2 error-- : " + e);
            return null;
        }
    }

}