package com.example.democode.domain.membership.dto;

import com.example.democode.domain.membership.model.MembershipType;
import com.example.democode.domain.membership.model.ValidationGroups;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MembershipRequest {

    @NotNull(groups = {ValidationGroups.MembershipAddMaker.class, ValidationGroups.MembershipAccumulateMaker.class})
    @Min(value = 0, groups = {ValidationGroups.MembershipAddMaker.class, ValidationGroups.MembershipAccumulateMaker.class})
    private final Integer point;

    @NotNull(groups = {ValidationGroups.MembershipAddMaker.class})
    private final MembershipType membershipType;

}
