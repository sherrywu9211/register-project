package com.demo.mnrRegister.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PersonResponse {

    private String systemUpdateTime;
    private int port;
    private String applyDate;
    private String englishName;
    private String chineseName;
    private String gender;
    private String faceImageReg;

    public String getSystemUpdateTime() {
        // 轉換 long > 日期
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(systemUpdateTime)), ZoneId.systemDefault());
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setSystemUpdateTime(String systemUpdateTime) {
        this.systemUpdateTime = systemUpdateTime;
    }

    public String getPort() {
        return port+"";
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getFaceImageReg() {
        return faceImageReg;
    }

    public void setFaceImageReg(String faceImageReg) {
        this.faceImageReg = faceImageReg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
