package com.demo.controller;

import com.demo.service.EmergencyRespService;
import com.demo.service.EmergencyRespServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class EmergencyRespController {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmergencyRespController.class);
    EmergencyRespService emergencyRespService = new EmergencyRespServiceImpl();

    // API URL
    // http://localhost:8080/api/MonToEnrEmergency?switchSystem=123&switchLocation=123

    @GetMapping("/MonToEnrEmergency")
    public String handleEmergencyRequest(String switchSystem, String switchLocation) {

        // 解碼 switchLocation 參數
        try {
            switchLocation = URLDecoder.decode(switchLocation, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "錯誤：switchLocation 解碼失敗";
        }
        return emergencyRespService.getRequest(switchSystem, switchLocation);
    }

}