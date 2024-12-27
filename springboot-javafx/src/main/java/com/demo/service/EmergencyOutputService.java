package com.demo.service;

import com.demo.model.EmergencyInputEntity;

public interface EmergencyOutputService {

    EmergencyInputEntity sendEmergencyRequest(String switchSystem, String switchLocation);

}