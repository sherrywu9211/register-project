package com.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramCheckerUtil {


    // 檢查程式是否啟動
    public static boolean isProgramRunning(String programName) {
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
            e.printStackTrace();
        }
        // 沒有找到程式名
        return false;
    }





}
