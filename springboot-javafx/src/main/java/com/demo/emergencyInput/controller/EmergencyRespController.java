package com.demo.emergencyInput.controller;

import com.demo.emergencyOutput.model.EmergencyRequest;
import com.demo.emergencyInput.service.EmergencyRespService;
import com.demo.emergencyInput.service.EmergencyRespServiceImpl;
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
        return emergencyRespService.getRequest(
                emergencyRequest.getSwitchSystem(), emergencyRequest.getSwitchLocation());
    }

}