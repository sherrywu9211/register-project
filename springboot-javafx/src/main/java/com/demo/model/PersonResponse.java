package com.demo.model;

public class PersonResponse {

    private String systemUpdateTime;
    private int port;
    private String applyDate;
    private String englishName;
    private String chineseName;
    private String faceImageReg;
    private String gender;

    public String getSystemUpdateTime() {
        return systemUpdateTime;
    }

    public void setSystemUpdateTime(String systemUpdateTime) {
        this.systemUpdateTime = systemUpdateTime;
    }

    public int getPort() {
        return port;
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
