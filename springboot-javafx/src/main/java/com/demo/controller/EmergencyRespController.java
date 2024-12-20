package com.demo.controller;

import com.demo.model.EmergencyRequest;
import com.demo.service.EmergencyRespService;
import com.demo.service.EmergencyRespServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmergencyRespController {

    EmergencyRespService emergencyRespService = new EmergencyRespServiceImpl();

    // API URL
    // http://localhost:8080/api/MonToEnrEmergency
    @PostMapping("/MonToEnrEmergency")
    public String handleEmergencyRequest(@RequestBody EmergencyRequest emergencyRequest) {
        return emergencyRespService.getRequest(emergencyRequest.getSwitchSystem(), emergencyRequest.getSwitchLocation());
    }

}