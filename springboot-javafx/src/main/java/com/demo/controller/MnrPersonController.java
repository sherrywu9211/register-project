package com.demo.controller;

import com.demo.model.MnrResponse;
import com.demo.model.PersonResponse;
import com.demo.service.MnrRegisterService;
import com.demo.service.MnrRegisterServiceImpl;

public class MnrPersonController extends MainController{

    MnrRegisterService mnrRegisterService = new MnrRegisterServiceImpl();

    public PersonResponse setData(MnrResponse mnrResponse){
        if (mnrResponse != null) {
            // todo
            // 呼叫API & 取得回應結果
            PersonResponse personResponse = mnrRegisterService.selectOneMnr(mnrResponse.getTravelId(), mnrResponse.getPassportNo(), mnrResponse.getSeqNo());


            // 顯示內容
            return null;
        }
        return null;

    }
}
