package com.evilcorp.service;

import com.evilcorp.dto.MyDto;
import com.evilcorp.dto.ResultDto;

public class MyService {
    public int getResult(MyDto dto) {
        ResultDto result = new ResultDto();
        switch (dto.getNumber()) {
            case 1:
                result.getNumber();
                return 2;
        }
        return 2;
    }
}
