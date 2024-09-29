package com.example.democode.global.common.dto;

import com.example.democode.global.enumeration.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResDto {
    private int resultCode = ResultCode.SUCCESS.getResultCode();
    private String resultMessage = ResultCode.SUCCESS.getResultMessage();
}
