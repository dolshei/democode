package com.example.democode.membership.repository;

import com.example.democode.membership.entity.Membership;
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
    public void MembershipRegist() {
        // given (준비) : 어떠한 데이터가 준비되었을 때
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipName("네이버")
                .point(10000)
                .build();

        // when (실행) : 어떠한 함수를 실행하면
        final Membership savedMembership = membershipRepository.save(membership);

        // then (검증) : 어떠한 결과가 나와야 한다.
        assertThat(savedMembership.getId()).isNotNull();
        assertThat(savedMembership.getUserId()).isEqualTo("userId");
        assertThat(savedMembership.getMembershipName()).isEqualTo("네이버");
        assertThat(savedMembership.getPoint()).isEqualTo(10000);
    }
}
