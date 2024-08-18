package com.example.democode.domain.membership.repository;

import com.example.democode.domain.membership.entity.Membership;
import com.example.democode.domain.membership.model.MembershipType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

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

    @DisplayName("멤버쉽조회_사이즈가0")
    @Test
    public void MembershipSearchSizeZeroTest() {
        // given (준비) : 어떠한 데이터가 준비되었을 때

        // when (실행) : 어떠한 함수를 실행하면
        List<Membership> result = membershipRepository.findAllByUserId("userId");

        // then (검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.size()).isEqualTo(0);

    }

    @DisplayName("멤버쉽조회_사이즈가2")
    @Test
    public void MembershipSearchSizeTwoTest() {
        // given (준비) : 어떠한 데이터가 준비되었을 때
        final Membership naverMembership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        final Membership kakaoMembership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.KAKAO)
                .point(10000)
                .build();

        membershipRepository.save(naverMembership);
        membershipRepository.save(kakaoMembership);

        // when (실행) : 어떠한 함수를 실행하면
        List<Membership> result = membershipRepository.findAllByUserId("userId");

        // then (검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.size()).isEqualTo(2);

    }
    
    @Test
    @DisplayName("멤버쉽추가후삭제")
    public void DeleteAfterAddingMembershipTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final Membership naverMembership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        final Membership savedMembership = membershipRepository.save(naverMembership);
        
        // when(실행) : 어떠한 함수를 실행하면
        membershipRepository.deleteById(savedMembership.getId());
        
        // then(검증) : 어떠한 결과가 나와야 한다.
        
    }
}
