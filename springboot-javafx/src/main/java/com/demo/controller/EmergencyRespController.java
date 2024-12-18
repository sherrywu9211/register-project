package com.demo.controller;

import com.demo.service.EmergencyRespService;
import com.demo.service.EmergencyRespServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmergencyRespController {

    EmergencyRespService emergencyRespService = new EmergencyRespServiceImpl();

    // API URL
    // http://localhost:8080/api/MonToEnrEmergency?switchSystem=123&switchLocation=123

    @GetMapping("/MonToEnrEmergency")
    public String handleEmergencyRequest(String switchSystem, String switchLocation) {
        return emergencyRespService.getRequest(switchSystem, switchLocation);
    }

}