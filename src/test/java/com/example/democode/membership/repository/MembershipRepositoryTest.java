package com.example.democode.membership.repository;

import com.example.democode.membership.entity.Membership;
import com.example.democode.membership.entity.MembershipType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MembershipRepositoryTest {
    @Autowired
    private MembershipRepository membershipRepository;
    
    @DisplayName("MembershipRepository 가 Null 이 아님")
    @Test
    public void MembershipRepositoryIsNotNull() {
        assertThat(membershipRepository).isNotNull();
    }

    @DisplayName("멤버쉽 등록")
    @Test
    public void MembershipRegistTest() {
        // given (준비) : 어떠한 데이터가 준비되었을 때
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        // when (실행) : 어떠한 함수를 실행하면
        final Membership savedMembership = membershipRepository.save(membership);

        // then (검증) : 어떠한 결과가 나와야 한다.
        assertThat(savedMembership.getId()).isNotNull();
        assertThat(savedMembership.getUserId()).isEqualTo("userId");
        assertThat(savedMembership.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(savedMembership.getPoint()).isEqualTo(10000);
    }

    @DisplayName("멤버쉽이 존재하는지 테스트")
    @Test
    public void MembershipExistTest() {
        // given (준비) : 어떠한 데이터가 준비되었을 때
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        // when (실행) : 어떠한 함수를 실행하면
        membershipRepository.save(membership);
        final Membership findMembership = membershipRepository.findByUserIdAndMembershipType("userId", MembershipType.NAVER);

        // then (검증) : 어떠한 결과가 나와야 한다.
        assertThat(findMembership).isNotNull();
        assertThat(findMembership.getId()).isNotNull();
        assertThat(findMembership.getUserId()).isEqualTo("userId");
        assertThat(findMembership.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(findMembership.getPoint()).isEqualTo(10000);
    }
}
