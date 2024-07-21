package com.example.democode.domain.membership.repository;

import com.example.democode.domain.membership.entity.Membership;
import com.example.democode.domain.membership.model.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Membership findByUserIdAndMembershipType(String userId, MembershipType membershipType);
}
