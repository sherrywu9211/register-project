package com.demo.emergencyInput.service;

import com.demo.emergencyInput.model.EmergencyResponse;
import com.demo.emergencyInput.model.GateLocation;
import com.demo.common.util.GateLocationUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class EmergencyRespServiceImpl implements EmergencyRespService {

    public static final Logger logger = LoggerFactory.getLogger(EmergencyRespServiceImpl.class);
    private static final List<String> VALID_LOCATIONS = Arrays.asList("00", "01", "02", "11", "12", "13");
    Gson gson = new Gson();

    @Override
    public String getRequest(String switchSystem, String switchLocation) {

        EmergencyResponse emergencyResp = new EmergencyResponse();

        // 取得目前的位置
        // 從設定檔取得位置 並設定至 currentGateLocation全域變數
        GateLocation.setCurrentGateLocation(GateLocationUtil.getLocation());
        String currentGateLocation = GateLocation.getCurrentGateLocation();

        // 固定回傳的內容 ip & timestamp
        String ip = "";
        try {
            // 取得本機的 InetAddress 物件
            InetAddress localHost = InetAddress.getLocalHost();
            // 取得本機的 IP 位址
            ip = localHost.getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println("無法取得本機的 IP 位址");
            logger.error(e.getMessage(), e);
        }
        emergencyResp.setTerminalIp(ip);
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        emergencyResp.setTerminalTime(timestamp);

        // 處理null
        switchSystem = (switchSystem == null || switchSystem.isEmpty()) ? "" : switchSystem;

        switch (switchSystem){
            case "MNR":
                if (VALID_LOCATIONS.contains(switchLocation) && !switchLocation.equals(currentGateLocation)) {
                    // MNR & 切換為不同的location
                    emergencyResp.setStatus("true");
                    // 設定 全域變數的值
                    GateLocation.setCurrentGateLocation(switchLocation);
                    // 設定 設定檔的值
                    GateLocationUtil.setLocation(GateLocation.getCurrentGateLocation());
                    // 從設定檔的值 拿出來顯示
                    emergencyResp.seteGateLocation(GateLocationUtil.getLocation());
                } else {
                    emergencyResp.setStatus("false");
                    emergencyResp.setMessage("機場代碼錯誤");
                }
                break;
            case "CHECK":
                // 回覆現在連線位置，switchLocation 不更改
                emergencyResp.setStatus("true");
                // 回覆目前的位置
                emergencyResp.seteGateLocation(currentGateLocation);
                break;
            default:
                emergencyResp.setStatus("false");
                emergencyResp.setMessage("系統代碼錯誤");
                break;
        }

        // 轉為json 回傳
        return gson.toJson(emergencyResp);
    }
}
