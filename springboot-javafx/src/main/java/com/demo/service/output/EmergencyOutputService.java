package com.demo.service.output;

import com.demo.model.EmergencyInputEntity;

public interface EmergencyOutputService {

    EmergencyInputEntity sendEmergencyRequest(String switchSystem, String switchLocation);

}