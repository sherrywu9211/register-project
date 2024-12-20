package com.demo.service;

import com.demo.model.EmergencyResponse;

public interface EmergencyReqService {

    EmergencyResponse changeLocation(String switchSystem, String switchLocation);

}
