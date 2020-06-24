package com.evilcorp.service;

import com.evilcorp.dto.MyDto;
import com.evilcorp.dto.ResultDto;
import com.evilcorp.enums.ResultStatus;

public class MyService {
    public ResultDto getResult(MyDto dto) {
        ResultDto result = new ResultDto();
        switch (dto.getStatus()) {
            case FINE:
                result.setResultStatus(ResultStatus.FIRST);
                return result;
        }
        return null;
    }
}
