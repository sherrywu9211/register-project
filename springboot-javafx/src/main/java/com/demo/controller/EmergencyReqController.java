package com.demo.controller;

import com.demo.model.EmergencyResponse;
import com.demo.service.EmergencyReqService;
import com.demo.service.EmergencyReqServiceImpl;
import com.demo.util.GateLocationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.Arrays;

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
//
        // 假設你已經將選項填充到 ChoiceBox 中，並且顯示的是如 "00-雲端正式"
        switchLocationChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String selectedCode = newValue.split("-")[0];  // 取得代號部分
//                System.out.println("選擇的代號是: " + selectedCode);
            }
        });



    }

    // 點擊按鈕 切換或回覆目前位置
    @FXML
    private void onApiButtonClick() {
        // 取得選項值
        String switchSystem = switchSystemChoiceBox.getValue();
        String switchLocation = GateLocationUtil.getCodeByLocationName(switchLocationChoiceBox.getValue());
        // 傳送參數
        EmergencyResponse emergencyResponse = emergencyReqService.changeLocation(switchSystem, switchLocation);
        // VIEW顯示值
        statusField.setText(emergencyResponse.getStatus());
        messageField.setText(emergencyResponse.getMessage());
        terminalIpField.setText(emergencyResponse.getTerminalIp());
        terminalTimeField.setText(emergencyResponse.getTerminalTime());
        eGateLocationField.setText(GateLocationUtil.getLocationNameByCode(emergencyResponse.geteGateLocation()));
        currentGateLocationField.setText(GateLocationUtil.getLocationNameByCode(GateLocationUtil.getLocation()));
    }

}