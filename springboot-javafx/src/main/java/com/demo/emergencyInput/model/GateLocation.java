package com.demo.emergencyInput.model;

public class GateLocation {
    // 靜態全域變數
    private static String currentGateLocation;

    public static String getCurrentGateLocation() {
        return currentGateLocation;
    }

    public static void setCurrentGateLocation(String currentGateLocation) {
        GateLocation.currentGateLocation = currentGateLocation;
    }
}
