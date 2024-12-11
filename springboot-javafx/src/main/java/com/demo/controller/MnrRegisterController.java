package com.demo.controller;

import com.demo.model.Resident;
import com.demo.util.MnrApiUtil;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.time.format.DateTimeFormatter;

@Controller
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

    @Autowired
    private MnrApiUtil mnrApiUtil;


    @FXML
    public void onSearchButtonClick() {
        // 把key in的資料 傳送參數到第一支api
        // 接收api回應參數 並顯示結果
        Resident resident = new Resident();
        if(!(passportNoField.getText().isEmpty())){
            resident.setPassportNo(passportNoField.getText());
        }
        if(!(permitNoField.getText().isEmpty())){
            resident.setPermitNo(permitNoField.getText());
        }
        if(!(terminalIdField.getText().isEmpty())){
            resident.setTerminalId(terminalIdField.getText().split(","));
        }
        if(!(travelIdField.getText().isEmpty())){
            resident.setTravelId(travelIdField.getText());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        if(applyDateSField.getValue() != null) {
            resident.setApplyDateS(applyDateSField.getValue().format(formatter));
        }
        if(applyDateEField.getValue() != null) {
            resident.setApplyDateE(applyDateEField.getValue().format(formatter));
        }
        // todo
        // 呼叫api方法
//        mnrApiUtil.MnrApi(resident);
//        System.out.println(mnrApiUtil.MnrApi(resident));

        // 生成 HBox > for迴圈 顯示註冊者資料

        // 綁定 註冊者卡片 點擊事件 > loadView 新fxml


        mnrResult.setText("register還沒開發完~");
    }

}