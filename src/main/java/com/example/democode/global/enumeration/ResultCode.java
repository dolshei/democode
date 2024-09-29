package com.example.democode.global.enumeration;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200, ResultMessage.SUCCESS),
    UNAUTHORIZED(401, ResultMessage.UNAUTHORIZED),
    NO_AUTH(403, ResultMessage.NO_AUTH),
    ACCESS_NO_AUTH(1_000, ResultMessage.ACCESS_NO_AUTH),
    ACCESS_TOKEN_EXPIRED(1_001, ResultMessage.ACCESS_TOKEN_EXPIRED),
    REFRESH_TOKEN_EXPIRED(1_002, ResultMessage.REFRESH_TOKEN_EXPIRED),
    VALID_NOT_PHONE_NUM(1_007, ResultMessage.VALID_NOT_PHONE_NUM),
    VALID_NOT_PASSWORD(1_008, ResultMessage.VALID_NOT_PASSWORD),
    MEMBER_NOT_EXIST(1_012, ResultMessage.MEMBER_NOT_EXIST),
    LOGIN_REQUIRED(1_019, ResultMessage.LOGIN_REQUIRED),
    INTERNAL_ERROR(500, ResultMessage.INTERNAL_ERROR),
    ;

    private final int resultCode;
    private final String resultMessage;

    ResultCode(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public interface ResultMessage {
        String SUCCESS = "완료 되었습니다.";
        String UNAUTHORIZED = "인증에 실패하였습니다.";
        String NO_AUTH = "접근 권한이 없습니다.";
        String ACCESS_NO_AUTH = "접근 권한이 없습니다.";
        String ACCESS_TOKEN_EXPIRED = "Access Token 이 만료 되었습니다.";
        String REFRESH_TOKEN_EXPIRED = "Refresh Token 이 만료 되었습니다.";
        String VALID_NOT_PHONE_NUM = "가입되지 않은 핸드폰 번호 입니다.";
        String VALID_NOT_PASSWORD = "잘못된 비밀번호 입니다.";
        String MEMBER_NOT_EXIST = "존재하지 않는 사용자입니다.";
        String LOGIN_REQUIRED = "로그인이 필요합니다.";
        String PARAM_NOT_VALID = "파라미터 오류입니다.";
        String INTERNAL_ERROR = "시스템 오류가 발생하였습니다. 다시 시도해 주세요.";
    }
}
