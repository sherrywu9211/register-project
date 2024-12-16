package com.demo.controller;

import com.demo.model.EmergencyResponse;
import org.springframework.web.bind.annotation.GetMapping;

public class EmergencyRespController {


    @GetMapping("/MonToEnrEmergency")
    public EmergencyResponse handleEmergencyRequest(String switchSystem, String switchLocation) {



        return null;
    }

}