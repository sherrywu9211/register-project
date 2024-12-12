package com.demo.service;

import com.demo.model.MnrResponse;
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

    @Override
    public List<MnrResponse> selectAllMnr(Resident resident) {
        // 傳送並取得 JSON格式的回應
        String jsonData = mnrApiUtil.MnrApi(resident);
        // 轉換格式
        Gson gson = new Gson();
        // 定義泛型類型
        Type listType = new TypeToken<List<MnrResponse>>() {}.getType();
        String resListJson = gson.fromJson(jsonData, JsonObject.class).get("resList").toString();
        List<MnrResponse> resList = gson.fromJson(resListJson, listType);

        return resList;
    }

    @Override
    public MnrResponse selectOneMnr(String travelId, String passportNo, String seqNo) {
        return null;
    }
}
