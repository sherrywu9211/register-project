package com.demo.service.input;

import com.demo.model.RegisterListEntity;
import com.demo.model.RegisterPersonEntity;
import com.demo.model.RegisterSelectEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RegisterService {

    List<RegisterListEntity> selectList(RegisterSelectEntity resident);
    RegisterPersonEntity selectOnePerson(String travelId, String passportNo, String seqNo);

}