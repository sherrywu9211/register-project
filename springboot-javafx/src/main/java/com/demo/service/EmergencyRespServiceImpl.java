package com.demo.service;

import com.demo.controller.BaseController;
import com.demo.model.EmergencyResponse;
import com.google.gson.Gson;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class EmergencyRespServiceImpl implements EmergencyRespService {

    private static final List<String> VALID_LOCATIONS = Arrays.asList("00", "01", "02", "11", "12", "13");
    Gson gson = new Gson();

    @Override
    public String getRequest(String switchSystem, String switchLocation) {

        EmergencyResponse emergencyReq = new EmergencyResponse();
        emergencyReq.seteGateLocation(BaseController.getLocation());
        System.out.println( "-------1----emergencyReq--location:"+ emergencyReq.geteGateLocation());

        EmergencyResponse emergencyResp = new EmergencyResponse();

        // 固定回傳的內容
        String ip = "";
        try {
            // 取得本機的 InetAddress 物件
            InetAddress localHost = InetAddress.getLocalHost();
            // 取得本機的 IP 位址
            ip = localHost.getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("無法取得本機的 IP 位址");
            e.printStackTrace();
        }
        emergencyResp.setTerminalIp(ip);
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        emergencyResp.setTerminalTime(timestamp);

        // todo  取得location
        if(!switchSystem.equals("CHECK") && !switchSystem.equals("MNR")) {
            emergencyResp.setStatus("false");
            // todo  set msg
            emergencyResp.setMessage("請求參數錯誤");
        } else if(switchSystem.equals("MNR")) {
            if( !VALID_LOCATIONS.contains(switchLocation) && !switchLocation.equals(emergencyReq.geteGateLocation()) ) {
                // MNR & 切換為不同的location
                emergencyResp.setStatus("true");
                emergencyResp.seteGateLocation(switchLocation);
            }else {
                emergencyResp.setStatus("false");
                emergencyResp.setMessage("切換機場代號錯誤");
            }
            // todo
        }else if (switchSystem.equals("CHECK")){
            // 回覆現在連線位置，switchLocation 不更改
            emergencyResp.setStatus("true");
            emergencyResp.seteGateLocation(emergencyReq.geteGateLocation());
        }

        // 轉為json 回傳
        return  gson.toJson(emergencyResp);
    }
}
