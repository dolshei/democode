package com.example.democode.domain.membership.exception;

import com.example.democode.domain.membership.model.MembershipErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MembershipException extends RuntimeException {
    private final MembershipErrorResult errorResult;
}
