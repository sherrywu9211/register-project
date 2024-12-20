package com.demo.controller;

import com.demo.model.EmergencyResponse;
import com.demo.service.EmergencyReqService;
import com.demo.service.EmergencyReqServiceImpl;
import com.demo.util.GateLocationUtil;
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
        currentGateLocationField.setText( GateLocationUtil.getLocation());
    }

    // 點擊按鈕 切換或回覆目前位置
    @FXML
    private void onApiButtonClick() {
        // 取得選項值
        String switchSystem = switchSystemChoiceBox.getValue();
        String switchLocation = switchLocationChoiceBox.getValue();
        // 傳送參數
        EmergencyResponse emergencyResponse = emergencyReqService.changeLocation(switchSystem, switchLocation);
        // VIEW顯示值
        statusField.setText(emergencyResponse.getStatus());
        messageField.setText(emergencyResponse.getMessage());
        terminalIpField.setText(emergencyResponse.getTerminalIp());
        terminalTimeField.setText(emergencyResponse.getTerminalTime());
        eGateLocationField.setText(emergencyResponse.geteGateLocation());
        currentGateLocationField.setText(GateLocationUtil.getLocation());
    }

}