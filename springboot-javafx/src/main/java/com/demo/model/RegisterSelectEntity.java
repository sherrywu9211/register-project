package com.demo.model;


import java.util.Arrays;

public class RegisterSelectEntity {

    private String passportNo = "";  // 護照號碼
    private String permitNo = "";    // 許可證號
    private String documentType = "";    // 證件類別
    private String[] terminalId = new String[] {}; // 閘門編號
    private String applyDateS = "";  // 註冊時間-起
    private String applyDateE = "";  // 註冊時間-迄
    private String travelId = "";    // travelId
    private String residentIdNo = "";
    private String selectImgType = ""; // 註冊生物特徵類別

    private int total = 1;          // 總數
    private int pageSize = 10;       // 每頁大小
    private int pageNumber = 1;     // 頁碼

    @Override
    public String toString() {
        return "Resident{" +
                "passportNo='" + passportNo + '\'' +
                ", permitNo='" + permitNo + '\'' +
                ", documentType='" + documentType + '\'' +
                ", terminalId=" + Arrays.toString(terminalId) +
                ", applyDateS='" + applyDateS + '\'' +
                ", applyDateE='" + applyDateE + '\'' +
                ", travelId='" + travelId + '\'' +
                ", total=" + total +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                '}';
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPermitNo() {
        return permitNo;
    }

    public void setPermitNo(String permitNo) {
        this.permitNo = permitNo;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String[] getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String[] terminalId) {
        this.terminalId = terminalId;
    }

    public String getApplyDateS() {
        return applyDateS;
    }

    public void setApplyDateS(String applyDateS) {
        this.applyDateS = applyDateS;
    }

    public String getApplyDateE() {
        return applyDateE;
    }

    public void setApplyDateE(String applyDateE) {
        this.applyDateE = applyDateE;
    }

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getResidentIdNo() {
        return residentIdNo;
    }

    public void setResidentIdNo(String residentIdNo) {
        this.residentIdNo = residentIdNo;
    }

    public String getSelectImgType() {
        return selectImgType;
    }

    public void setSelectImgType(String selectImgType) {
        this.selectImgType = selectImgType;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}