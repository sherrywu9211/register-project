package com.demo.controller;

import com.demo.models.Resident;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MnrRegisterController {

    @FXML
    private TextField passportNoField;
    @FXML
    private TextField permitNoField;
    @FXML
    private TextField terminalIdField;
    @FXML
    private DatePicker applyDateSField;
    @FXML
    private DatePicker applyDateEField;
    @FXML
    private TextField travelIdField;
    @FXML
    private Label mnrResult;


    @FXML
    protected void onSearchButtonClick() {
        // 把key in的資料 傳送參數到第一支api
        // 接收api回應參數 並顯示結果
        Resident resident = new Resident();

        resident.setPassportNo(passportNoField.getText());
        resident.setPermitNo(permitNoField.getText());
        String[] terminalIds = terminalIdField.getText().split(",");
//        for(String id : terminalIds){
//          resident.setTerminalId(id);
//        }
        if (applyDateSField.getValue() != null) {
          resident.setApplyDateS(applyDateSField.getValue().toString());
        }
        if (applyDateEField.getValue() != null) {
          resident.setApplyDateE(applyDateEField.getValue().toString());
        }
        System.out.println(resident.getPassportNo());
        mnrResult.setText("register還沒開發完~");
    }

}
