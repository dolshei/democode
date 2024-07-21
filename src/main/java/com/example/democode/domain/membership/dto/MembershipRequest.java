package com.example.democode.domain.membership.dto;

import com.example.democode.domain.membership.model.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MembershipRequest {

    private final Integer point;
    private final MembershipType membershipType;

}
