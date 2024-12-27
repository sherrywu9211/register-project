package com.demo.emergencyOutput.controller;

import com.demo.emergencyInput.model.EmergencyResponse;
import com.demo.emergencyOutput.service.EmergencyReqService;
import com.demo.emergencyOutput.service.EmergencyReqServiceImpl;
import com.demo.common.util.GateLocationUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class EmergencyReqController {

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
    EmergencyReqService emergencyReqService = new EmergencyReqServiceImpl();

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
        // 取得目前位置
        String currentGateLocation = GateLocationUtil.getLocation();

        // 傳送參數
        EmergencyResponse emergencyResponse = emergencyReqService.changeLocation(switchSystem, switchLocation);
        // VIEW顯示值
        statusField.setText(emergencyResponse.getStatus());
        messageField.setText(emergencyResponse.getMessage());
        terminalIpField.setText(emergencyResponse.getTerminalIp());
        terminalTimeField.setText(emergencyResponse.getTerminalTime());
        eGateLocationField.setText(GateLocationUtil.getLocationNameByCode(GateLocationUtil.getLocation()));

        // code轉換文字
        currentGateLocationField.setText( GateLocationUtil.getLocationNameByCode(GateLocationUtil.getLocation()));
    }

}