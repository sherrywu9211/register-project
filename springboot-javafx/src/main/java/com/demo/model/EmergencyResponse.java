package com.demo.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class EmergencyResponse {
    private String status;
    private String message;
    private String terminalIp;
    private long terminalTime;
    private String eGateLocation;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTerminalIp() {
        return terminalIp;
    }

    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp;
    }

    public String getTerminalTime() {
        // 轉換 long > 日期
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(terminalTime), ZoneId.systemDefault());
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setTerminalTime(int terminalTime) {
        this.terminalTime = terminalTime;
    }

    public String geteGateLocation() {
        return eGateLocation;
    }

    public void seteGateLocation(String eGateLocation) {
        this.eGateLocation = eGateLocation;
    }
}
