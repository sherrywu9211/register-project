package com.demo.controller;

import com.demo.model.EmergencyInputEntity;
import com.demo.service.output.EmergencyOutputService;
import com.demo.service.output.EmergencyOutputServiceImpl;
import com.demo.util.GateLocationUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class EmergencyOutputViewController {

    @FXML
    private Label currentGateLocationField;
    @FXML
    private ChoiceBox<String> switchSystemChoiceBox;
    @FXML
    private ChoiceBox<String> switchLocationChoiceBox;
    @FXML
    private Label statusField;
    @FXML
    private Label messageField;
    @FXML
    private Label terminalIpField;
    @FXML
    private Label terminalTimeField;
    @FXML
    private Label eGateLocationField;
    EmergencyOutputService emergencyOutputService = new EmergencyOutputServiceImpl();

    // 顯示目前位置
    @FXML
    private void initialize() {
        String currentGateLocation = GateLocationUtil.getLocation();
        // code轉換文字
        currentGateLocationField.setText( GateLocationUtil.getLocationNameByCode(currentGateLocation));
    }

    // 點擊按鈕 切換或回覆目前位置
    @FXML
    private void onApiButtonClick() {
        // 取得選項值
        String switchSystem = switchSystemChoiceBox.getValue();
        String switchLocation = GateLocationUtil.getCodeByLocationName(switchLocationChoiceBox.getValue());

        // 傳送參數
        EmergencyInputEntity emergencyInputEntity = emergencyOutputService.sendEmergencyRequest(switchSystem, switchLocation);
        if (emergencyInputEntity != null) {
            // VIEW顯示值
            statusField.setText(emergencyInputEntity.getStatus());
            messageField.setText(emergencyInputEntity.getMessage());
            terminalIpField.setText(emergencyInputEntity.getTerminalIp());
            terminalTimeField.setText(emergencyInputEntity.getTerminalTime());
            eGateLocationField.setText(GateLocationUtil.getLocationNameByCode(GateLocationUtil.getLocation()));

            // code轉換文字
            currentGateLocationField.setText( GateLocationUtil.getLocationNameByCode(GateLocationUtil.getLocation()));
        }else {
            eGateLocationField.setText("連線失敗");
        }
    }

}