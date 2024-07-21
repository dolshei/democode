package com.example.democode.membership.service;

import com.example.democode.membership.entity.Membership;
import com.example.democode.membership.entity.MembershipErrorResult;
import com.example.democode.membership.entity.MembershipType;
import com.example.democode.membership.exception.MembershipException;
import com.example.democode.membership.repository.MembershipRepository;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {

    private MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public Membership addMembership(final String userId, final MembershipType membershipType, final Integer point) {
        final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if (result != null) {
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        final Membership membership = Membership.builder()
                .userId(userId)
                .point(point)
                .membershipType(membershipType)
                .build();

        return membershipRepository.save(membership);
    }
}
