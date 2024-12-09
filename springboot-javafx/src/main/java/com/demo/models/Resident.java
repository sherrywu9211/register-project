package com.demo.models;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Resident {

    private String passportNo;  // 護照號碼
    private String permitNo;    // 許可證號
    private String documentType;    // 證件類別 非必填 預設空字串
    private String[] terminalId; // 閘門編號
    private String applyDateS;  // 註冊時間-起
    private String applyDateE;  // 註冊時間-迄
    private String travelId;    // travelId
    private int total;          // 總數
    private int pageSize;       // 每頁大小
    private int pageNumber;     // 頁碼

}
