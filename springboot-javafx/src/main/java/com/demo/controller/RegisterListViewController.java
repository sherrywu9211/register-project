package com.demo.controller;

import com.demo.model.RegisterListEntity;
import com.demo.model.RegisterSelectEntity;
import com.demo.service.input.RegisterService;
import com.demo.service.input.RegisterServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class RegisterListViewController {

    public static final Logger logger = LoggerFactory.getLogger(RegisterListViewController.class);

    // 第一支API使用的FXML欄位
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
    private Label resultField;

    RegisterService registerService = new RegisterServiceImpl();

    // 第二支API使用的FXML欄位
    @FXML
    private TableView<RegisterListEntity> resListTableView;
    @FXML
    private TableColumn<RegisterListEntity, String> systemUpdateTimeColumn;
    @FXML
    private TableColumn<RegisterListEntity, Integer> portColumn;
    @FXML
    private TableColumn<RegisterListEntity, String> passportNoColumn;
    @FXML
    private TableColumn<RegisterListEntity, String> chineseNameColumn;
    @FXML
    private TableColumn<RegisterListEntity, String> englishNameColumn;
    @FXML
    private TableColumn<RegisterListEntity, String> applyDateColumn;

    // 第一支API
    // 1. 接收view的select值  2.發送&接收API 3.view顯示結果
    @FXML
    public void selectButtonClick() {
        RegisterSelectEntity selectRegisterEntity = new RegisterSelectEntity();

        // 判斷欄位是否有值 並放入傳送的物件
        if(!(passportNoField.getText().isEmpty())){
            selectRegisterEntity.setPassportNo(passportNoField.getText());
        }
        if(!(permitNoField.getText().isEmpty())){
            selectRegisterEntity.setPermitNo(permitNoField.getText());
        }
        if(!(terminalIdField.getText().isEmpty())){
            selectRegisterEntity.setTerminalId(terminalIdField.getText().split(","));
        }
        if(!(travelIdField.getText().isEmpty())){
            selectRegisterEntity.setTravelId(travelIdField.getText());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        if(applyDateSField.getValue() != null) {
            selectRegisterEntity.setApplyDateS(applyDateSField.getValue().format(formatter));
        }
        if(applyDateEField.getValue() != null) {
            selectRegisterEntity.setApplyDateE(applyDateEField.getValue().format(formatter));
        }

        // 呼叫API方法
        List<RegisterListEntity> resList = registerService.selectList(selectRegisterEntity);
        if(resList != null && !resList.isEmpty()){
            // 顯示註冊者資料
            systemUpdateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("systemUpdateTime"));
            portColumn.setCellValueFactory(new PropertyValueFactory<>("port"));
            passportNoColumn.setCellValueFactory(new PropertyValueFactory<>("passportNo"));
            chineseNameColumn.setCellValueFactory(new PropertyValueFactory<>("chineseName"));
            englishNameColumn.setCellValueFactory(new PropertyValueFactory<>("englishName"));
            applyDateColumn.setCellValueFactory(new PropertyValueFactory<>("applyDate"));

            // 將資料加入 TableView & 呈現查詢結果
            ObservableList<RegisterListEntity> data = FXCollections.observableArrayList(resList);
            resListTableView.setItems(data);
        }else {
            resultField.setText("查詢失敗");
        }
    }

    // 第二支API resListTableView 的點擊事件
    @FXML
    public void initialize(){
        resListTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // 單擊
                RegisterListEntity selectedData = resListTableView.getSelectionModel().getSelectedItem();
                if (selectedData != null) {
                    // 當點擊某一筆資料後，顯示詳細資訊的 FXML
                    showPerson(selectedData);
                }
            }
        });
    }
    // 第二支API 顯示單筆DATA視窗
    private static final String MNR_PERSON_VIEW = "/static/views/registerPersonView.fxml";
    public void showPerson(RegisterListEntity mnrResponse) {
        FXMLLoader loader = null;
        try {
            // 顯示畫面
            loader = new FXMLLoader(getClass().getResource(MNR_PERSON_VIEW));
            Parent root = loader.load();
            Scene scene = new Scene(root, 400, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("----API 2 Error showPerson-----" + e);
            logger.error(e.getMessage(), e);
        }
        // 設定controller & 傳入data
        RegisterPersonViewController mnrPersonController = loader.getController();
        mnrPersonController.setData(mnrResponse);
    }

}