package com.demo.model;


public class MnrResponse {

    // 回應參數
    private long systemUpdateTime;
    private int port;
    private String passportNo;
    private String chineseName;
    private String englishName;
    private String applyDate;


    public long getSystemUpdateTime() {
        return systemUpdateTime;
    }

    public void setSystemUpdateTime(int systemUpdateTime) {
        this.systemUpdateTime = systemUpdateTime;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }
}
