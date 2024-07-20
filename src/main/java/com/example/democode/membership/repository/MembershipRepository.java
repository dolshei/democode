package com.example.democode.membership.repository;

import com.example.democode.membership.entity.Membership;
import com.example.democode.membership.entity.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Membership findByUserIdAndMembershipType(String userId, MembershipType membershipType);
}
