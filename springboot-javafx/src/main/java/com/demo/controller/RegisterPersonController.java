package com.demo.controller;

import com.demo.model.RegisterListEntity;
import com.demo.model.RegisterPersonEntity;
import com.demo.service.RegisterService;
import com.demo.service.RegisterServiceImpl;
import com.demo.util.ImageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegisterPersonController {

    @FXML
    private Label systemUpdateTimeField;
    @FXML
    private Label portField;
    @FXML
    private Label applyDateField;
    @FXML
    private Label englishNameField;
    @FXML
    private Label chineseNameField;
    @FXML
    private Label genderField;
    @FXML
    private ImageView faceImageRegField;

    RegisterService registerService = new RegisterServiceImpl();

    public void setData(RegisterListEntity registerListEntity){

        // 呼叫API & 取得回應結果
        RegisterPersonEntity registerPerson = registerService.selectOnePerson(
                registerListEntity.getTravelId(), registerListEntity.getPassportNo(), registerListEntity.getSeqNo());

        // 顯示內容
        if(registerPerson != null){
            systemUpdateTimeField.setText(registerPerson.getSystemUpdateTime());
            portField.setText(registerPerson.getPort());
            applyDateField.setText(registerPerson.getApplyDate());
            englishNameField.setText(registerPerson.getEnglishName());
            chineseNameField.setText(registerPerson.getChineseName());
            genderField.setText(registerPerson.getGender());
            Image faceImage = ImageUtil.base64ToImage(registerPerson.getFaceImageReg());
            faceImageRegField.setImage(faceImage);
        }
    }

}