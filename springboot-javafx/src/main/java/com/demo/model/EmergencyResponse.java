package com.demo.model;

public class EmergencyResponse {
    private String status;
    private String message;
    private String terminalIp;
    private int terminalTime;
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

    public int getTerminalTime() {
        return terminalTime;
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
