package com.demo.service;

import com.demo.model.MnrResponse;
import com.demo.model.Resident;
import com.demo.util.MnrApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MnrRegisterServiceImpl implements MnrRegisterService {

    @Autowired
    private MnrApiUtil mnrApiUtil;

    @Override
    public List<MnrResponse> selectAllMnr(Resident resident) {
//        mnrApiUtil.MnrApi(resident);


        return List.of();
    }

    @Override
    public MnrResponse selectOneMnr(String travelId, String passportNo, String seqNo) {
        return null;
    }
}
