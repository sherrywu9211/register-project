package com.demo.controller;

import com.demo.model.MnrResponse;
import com.demo.model.Resident;
import com.demo.service.MnrRegisterService;
import com.demo.service.MnrRegisterServiceImpl;
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
public class MnrRegisterController {

    public static final Logger logger = LoggerFactory.getLogger(MnrRegisterController.class);

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
    }

    @FXML
    public void initialize(){
        // 設定 resListTableView 的點擊事件
        resListTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // 單擊
                MnrResponse selectedData = resListTableView.getSelectionModel().getSelectedItem();
                if (selectedData != null) {
                    // 當點擊某一筆資料後，顯示詳細資訊的 FXML
                    personClick(selectedData);
                }
            }
        });
    }

    private static final String MNR_PERSON_VIEW = "/static/views/mnrPerson.fxml";
    public void personClick(MnrResponse mnrResponse) {
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
            System.err.println("-----Error personClick-----");
            logger.error(e.getMessage(), e);
        }
        // 設定controller & 傳入data
        MnrPersonController mnrPersonController = loader.getController();
        mnrPersonController.setData(mnrResponse);
    }

}