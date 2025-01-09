package com.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static com.demo.util.showErrorUtil.showErrorAlert;

public class ProgramCheckerUtil {

    private static final Logger logger = LoggerFactory.getLogger(ProgramCheckerUtil.class);
    private static final String WEBSOCKET_APP_URL = "C:/workspace/websocket-project/target/websocket-project/websocket-project.exe";

    // 檢查程式是否啟動
    public static boolean isServerRunning(String programName) {
        try {
            // 執行Windows-tasklist 指令
            Process process = Runtime.getRuntime().exec("tasklist");
            InputStreamReader isr= new InputStreamReader(process.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null){
                if(line.contains(programName)){
                    // 找到程式名
                    return true;
                }
            }
        } catch (IOException e) {
            logger.error("找不到執行程式: " + e);
        }
        // 沒有找到程式名
        return false;
    }

    // 嘗試啟動程式
    public static boolean startWebsocketProjectServer() {
        logger.warn("server端未啟動，client端 嘗試啟動server端");
        try {
            // 1 如果未啟動 則嘗試啟動server端
            Process process = Runtime.getRuntime().exec(WEBSOCKET_APP_URL);
            process.waitFor();
            logger.info("server端程式 啟動成功");
            return true;
        }catch (Exception e) {
            // 2. 如果啟動失敗 顯示錯誤提醒
            showErrorAlert("server端程式 啟動失敗，請稍後再試");
            logger.error("server端程式 啟動失敗: " + e);
            return false;
        }
    }

}