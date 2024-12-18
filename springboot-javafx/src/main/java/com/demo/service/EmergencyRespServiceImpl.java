package com.demo.service;

import com.demo.model.EmergencyResponse;
import com.google.gson.Gson;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class EmergencyRespServiceImpl implements EmergencyRespService {

    Gson gson = new Gson();
    EmergencyResponse emergencyResp = new EmergencyResponse();

    @Override
    public String getRequest(String switchSystem, String switchLocation) {
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
        if(switchSystem.isEmpty() || switchLocation.isEmpty()) {
            emergencyResp.setStatus("false");
            // todo  set msg
            emergencyResp.setMessage("請求參數不可空值");
            emergencyResp.seteGateLocation("錯誤");
        } else if(switchSystem.equals("MNR") && !(switchLocation.equals(emergencyResp.geteGateLocation()))) {
            // MNR & 切換為不同的location
            emergencyResp.setStatus("true");
            emergencyResp.seteGateLocation(switchLocation);
        }else if (switchSystem.equals("CHECK")){
            // 回覆現在連線位置，switchLocation 不更改
            emergencyResp.setStatus("true");
            emergencyResp.seteGateLocation(emergencyResp.geteGateLocation());
        }

        // 轉為json 回傳
        return  gson.toJson(emergencyResp);
    }
}
