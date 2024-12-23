package com.demo.controller;

import com.demo.model.MnrResponse;
import com.demo.model.PersonResponse;
import com.demo.service.MnrRegisterService;
import com.demo.service.MnrRegisterServiceImpl;
import com.demo.util.ImageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MnrPersonController {

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

    MnrRegisterService mnrRegisterService = new MnrRegisterServiceImpl();

    public void setData(MnrResponse mnrResponse){
        // 呼叫API & 取得回應結果
        PersonResponse personResponse = mnrRegisterService.selectOneMnr(
                mnrResponse.getTravelId(), mnrResponse.getPassportNo(), mnrResponse.getSeqNo());
        // 顯示內容
        systemUpdateTimeField.setText(personResponse.getSystemUpdateTime());
        portField.setText(personResponse.getPort());
        applyDateField.setText(personResponse.getApplyDate());
        englishNameField.setText(personResponse.getEnglishName());
        chineseNameField.setText(personResponse.getChineseName());
        genderField.setText(personResponse.getGender());
        Image faceImage = ImageUtil.base64ToImage(personResponse.getFaceImageReg());
        faceImageRegField.setImage(faceImage);
    }

}