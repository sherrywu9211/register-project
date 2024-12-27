package com.demo.mnrRegister.sevice;

import com.demo.mnrRegister.model.MnrResponse;
import com.demo.mnrRegister.model.PersonResponse;
import com.demo.mnrRegister.model.Resident;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegisterService {

    List<MnrResponse> selectAllMnr(Resident resident);
    PersonResponse selectOneMnr(String travelId, String passportNo, String seqNo);

}
