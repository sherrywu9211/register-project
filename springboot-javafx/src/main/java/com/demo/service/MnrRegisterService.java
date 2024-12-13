package com.demo.service;

import com.demo.model.MnrResponse;
import com.demo.model.PersonResponse;
import com.demo.model.Resident;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MnrRegisterService {

    List<MnrResponse> selectAllMnr(Resident resident);

    PersonResponse selectOneMnr(String travelId, String passportNo, String seqNo);

}
