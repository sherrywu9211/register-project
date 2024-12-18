package com.demo.service;

import com.demo.model.EmergencyResponse;
import com.demo.util.MnrApiUtil;
import com.google.gson.Gson;

public class EmergencyServiceImpl implements EmergencyService {

    MnrApiUtil mnrApiUtil = new MnrApiUtil();
    Gson gson = new Gson();

    @Override
    public EmergencyResponse changeStatus(String switchSystem, String switchLocation) {
        String jsonResponse = mnrApiUtil.changEmergencyApi(switchSystem, switchLocation);
        return gson.fromJson(jsonResponse, EmergencyResponse.class);
    }

}