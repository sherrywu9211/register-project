package com.demo.service;

import com.demo.model.MnrResponse;
import com.demo.model.PersonResponse;
import com.demo.model.Resident;
import com.demo.util.MnrApiUtil;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;

@Service
public class MnrRegisterServiceImpl implements MnrRegisterService {

    MnrApiUtil mnrApiUtil = new MnrApiUtil();
    Gson gson = new Gson();

    @Override
    public List<MnrResponse> selectAllMnr(Resident resident) {
        // 傳送並取得 JSON格式的回應
        String jsonData = mnrApiUtil.mnrApi(resident);

        // 定義泛型類型
        Type listType = new TypeToken<List<MnrResponse>>() {}.getType();
        String resListJson = gson.fromJson(jsonData, JsonObject.class).get("resList").toString();

        return gson.fromJson(resListJson, listType);
    }

    @Override
    public PersonResponse selectOneMnr(String travelId, String passportNo, String seqNo) {
        // 呼叫API & 取得回應結果
        String responseJson = mnrApiUtil.oneMnrApi(travelId, passportNo, seqNo);
        System.out.println(responseJson);
        return gson.fromJson(responseJson, PersonResponse.class);
    }
}
