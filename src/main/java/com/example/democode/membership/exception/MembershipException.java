package com.example.democode.membership.exception;

import com.example.democode.membership.entity.MembershipErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MembershipException extends RuntimeException {
    private final MembershipErrorResult errorResult;
}
