package com.example.democode.domain.membership.repository;

import com.example.democode.domain.membership.dto.MembershipDetailResponse;
import com.example.democode.domain.membership.entity.Membership;
import com.example.democode.domain.membership.model.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Membership findByUserIdAndMembershipType(String userId, MembershipType membershipType);

    List<Membership> findAllByUserId(String userId);
}
