package com.demo.controller;

import com.demo.model.MnrResponse;
import com.demo.model.Resident;
import com.demo.service.MnrRegisterService;
import com.demo.service.MnrRegisterServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MnrRegisterController extends MainController {

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

    MnrRegisterService mnrRegisterService = new MnrRegisterServiceImpl();

    @FXML
    private TableView<MnrResponse> resListTableView;
    @FXML
    private TableColumn<MnrResponse, String> systemUpdateTimeColumn;
    @FXML
    private TableColumn<MnrResponse, Integer> portColumn;
    @FXML
    private TableColumn<MnrResponse, String> passportNoColumn;
    @FXML
    private TableColumn<MnrResponse, String> chineseNameColumn;
    @FXML
    private TableColumn<MnrResponse, String> englishNameColumn;
    @FXML
    private TableColumn<MnrResponse, String> applyDateColumn;

    // 第一支api
    @FXML
    public void selectAllButtonClick() {
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
        // 呼叫api方法
        List<MnrResponse> resList = mnrRegisterService.selectAllMnr(resident);
        // 顯示註冊者資料
        systemUpdateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("systemUpdateTime"));
        portColumn.setCellValueFactory(new PropertyValueFactory<>("port"));
        passportNoColumn.setCellValueFactory(new PropertyValueFactory<>("passportNo"));
        chineseNameColumn.setCellValueFactory(new PropertyValueFactory<>("chineseName"));
        englishNameColumn.setCellValueFactory(new PropertyValueFactory<>("englishName"));
        applyDateColumn.setCellValueFactory(new PropertyValueFactory<>("applyDate"));
        // 將資料加入 TableView & 呈現查詢結果
        ObservableList<MnrResponse> data = FXCollections.observableArrayList(resList);
        resListTableView.setItems(data);

        // 綁定 註冊者卡片 點擊事件 > loadView 新fxml
    }

}