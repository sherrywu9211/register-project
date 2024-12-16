package com.demo.service;

import com.demo.model.EmergencyResponse;

public interface EmergencyService {

    EmergencyResponse changeStatus(String switchSystem, String switchLocation);

}
