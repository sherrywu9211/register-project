package com.demo.service;

import com.demo.model.EmergencyRequest;
import com.demo.model.EmergencyResponse;
import com.demo.util.MnrApiUtil;
import com.google.gson.Gson;

public class EmergencyReqServiceImpl implements EmergencyReqService {

    MnrApiUtil mnrApiUtil = new MnrApiUtil();
    Gson gson = new Gson();

    @Override
    public EmergencyResponse changeLocation(String switchSystem, String switchLocation) {

        EmergencyRequest emergencyRequest = new EmergencyRequest();
        emergencyRequest.setSwitchSystem(switchSystem);
        emergencyRequest.setSwitchLocation(switchLocation);

        String jsonResponse = mnrApiUtil.changEmergencyApi(emergencyRequest);

        return gson.fromJson(jsonResponse, EmergencyResponse.class);
    }

}