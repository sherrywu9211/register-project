package com.demo.controller;

import com.demo.model.EmergencyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

public class EmergencyRespController {


    @GetMapping("/MonToEnrEmergency")
    public EmergencyResponse handleEmergencyRequest(String switchSystem, String switchLocation) {

        return null;
    }
    private static final Logger logger = LoggerFactory.getLogger(EmergencyRespController.class);

    @GetMapping("/")
    public String testLog() {
        logger.trace("這是 TRACE 日誌");
        logger.debug("這是 DEBUG 日誌");
        logger.info("這是 INFO 日誌");
        logger.warn("這是 WARN 日誌");
        logger.error("這是 ERROR 日誌");
        return "日誌測試完成";
    }
}