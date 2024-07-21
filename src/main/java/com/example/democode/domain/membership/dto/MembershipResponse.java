package com.example.democode.domain.membership.dto;

import com.example.democode.domain.membership.model.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class MembershipResponse {

    private final Long id;
    private final MembershipType membershipType;
}