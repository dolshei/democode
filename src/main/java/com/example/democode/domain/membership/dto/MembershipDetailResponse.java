package com.example.democode.domain.membership.dto;

import com.example.democode.domain.membership.model.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class MembershipDetailResponse {

    private final Long id;
    private final MembershipType membershipType;
    private final int point;
    private final LocalDateTime createdAt;
}