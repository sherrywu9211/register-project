package com.demo.emergencyOutput.service;

import com.demo.emergencyInput.model.EmergencyResponse;

public interface EmergencyReqService {

    EmergencyResponse changeLocation(String switchSystem, String switchLocation);

}
