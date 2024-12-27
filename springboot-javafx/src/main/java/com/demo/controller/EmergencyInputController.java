package com.demo.controller;

import com.demo.model.EmergencyOutputEntity;
import com.demo.service.EmergencyInputService;
import com.demo.service.EmergencyInputServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmergencyInputController {

    EmergencyInputService emergencyRespService = new EmergencyInputServiceImpl();

    // API URL
    // http://localhost:8080/api/MonToEnrEmergency
    @PostMapping("/MonToEnrEmergency")
    public String handleEmergencyRequest(@RequestBody EmergencyOutputEntity emergencyRequest) {
        return emergencyRespService.receiveRequest(
                emergencyRequest.getSwitchSystem(), emergencyRequest.getSwitchLocation());
    }

}