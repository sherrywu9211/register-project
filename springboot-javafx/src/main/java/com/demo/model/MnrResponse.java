package com.demo.model;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MnrResponse {

    // 回應參數
    private long systemUpdateTime;
    private int port;
    private String passportNo;
    private String chineseName;
    private String englishName;
    private String applyDate;


    public String getSystemUpdateTime() {
        // 轉換 long > 日期
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(systemUpdateTime), ZoneId.systemDefault());
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
