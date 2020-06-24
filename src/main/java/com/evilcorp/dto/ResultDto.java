package com.evilcorp.dto;

import com.evilcorp.enums.ResultStatus;

public class ResultDto {
    ResultStatus resultStatus;

    public ResultDto() {
    }

    public ResultStatus getResultStatus() {
        return this.resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }
}
