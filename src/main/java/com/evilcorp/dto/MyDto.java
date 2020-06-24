package com.evilcorp.dto;

import com.evilcorp.enums.MyStatus;
import lombok.Data;

@Data
public class MyDto {
    MyStatus status;
    int number;
}
