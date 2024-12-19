package com.demo.controller;

import com.demo.model.EmergencyResponse;
import com.demo.service.EmergencyService;
import com.demo.service.EmergencyServiceImpl;
import com.demo.util.GateLocationUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;


public class EmergencyReqController {

    @FXML
    private ChoiceBox<String> switchSystemChoiceBox;
    @FXML
    private ChoiceBox<String> switchLocationChoiceBox;

    EmergencyService emergencyService = new EmergencyServiceImpl();

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


    @FXML
    private Label currentGateLocationField;

    @FXML
    private void initialize() {
        currentGateLocationField.setText( GateLocationUtil.getLocation());
    }

    @FXML
    private void onApiButtonClick() {
        // 取得選項值
        String switchSystem = switchSystemChoiceBox.getValue();
        String switchLocation = switchLocationChoiceBox.getValue();
        // 傳送參數
        EmergencyResponse emergencyResponse = emergencyService.changeStatus(switchSystem, switchLocation);
        // VIEW顯示值
        statusField.setText(emergencyResponse.getStatus());
        messageField.setText(emergencyResponse.getMessage());
        terminalIpField.setText(emergencyResponse.getTerminalIp());
        terminalTimeField.setText(emergencyResponse.getTerminalTime());
        eGateLocationField.setText(emergencyResponse.geteGateLocation());
        currentGateLocationField.setText(GateLocationUtil.getLocation());
    }

}