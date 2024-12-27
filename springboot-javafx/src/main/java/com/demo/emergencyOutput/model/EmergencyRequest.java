package com.demo.emergencyOutput.model;

public class EmergencyRequest {

    private String switchSystem;
    private String switchLocation;

    public String getSwitchSystem() {
        return switchSystem;
    }

    public void setSwitchSystem(String switchSystem) {
        this.switchSystem = switchSystem;
    }

    public String getSwitchLocation() {
        return switchLocation;
    }

    public void setSwitchLocation(String switchLocation) {
        this.switchLocation = switchLocation;
    }
}
