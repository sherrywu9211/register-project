package com.demo.controller;

import com.demo.model.EmergencyResponse;
import com.demo.service.EmergencyService;
import com.demo.service.EmergencyServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class EmergencyReqController {

    @FXML
    private ChoiceBox<String> switchSystemChoiceBox;
    @FXML
    private ChoiceBox<String> switchLocationChoiceBox;

    EmergencyService emergencyService = new EmergencyServiceImpl();

    @FXML
    protected void onApiButtonClick() {

        String switchSystem = switchSystemChoiceBox.getValue();
        String switchLocation = switchLocationChoiceBox.getValue();
        EmergencyResponse emergencyResponse = emergencyService.changeStatus(switchSystem, switchLocation);
        // 顯示值
        // todo

    }


}
