package com.demo.model;

public enum GateLocationCode {

    CLOUD("00", "雲端正式"),
    TPE_T1("01", "桃園機場第一航廈"),
    KHH("02", "高雄小港機場"),
    TSA("11", "台北松山機場"),
    TPE_T2("12", "桃園機場第二航廈"),
    KINMEN("13", "金門港");

    private final String code;
    private final String description;

    GateLocationCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescriptionByCode(String code) {
        for (GateLocationCode location : values()) {
            if (location.code.equals(code)) {
                return location.description;
            }
        }
        return null;
    }
}
