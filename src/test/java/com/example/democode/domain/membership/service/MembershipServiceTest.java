package com.example.democode.domain.membership.service;

import com.example.democode.domain.membership.dto.MembershipAddResponse;
import com.example.democode.domain.membership.dto.MembershipDetailResponse;
import com.example.democode.domain.membership.entity.Membership;
import com.example.democode.domain.membership.exception.MembershipException;
import com.example.democode.domain.membership.model.MembershipErrorResult;
import com.example.democode.domain.membership.model.MembershipType;
import com.example.democode.domain.membership.repository.MembershipRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class MembershipServiceTest {

    private static final Logger log = LoggerFactory.getLogger(MembershipServiceTest.class);
    // MembershipService는 테스트 대상이므로 의존성이 주입되는 어노테이션인 @InjectMocks를 붙여주었고
    @InjectMocks
    private MembershipService membershipService;

    // MembershipRepository가 의존성이 있는 클래스이므로 가짜 객체 생성을 도와주는 @Mock를 붙여주었다.
    @Mock
    private MembershipRepository membershipRepository;

    @Mock
    private RatePointService ratePointService;

    private final Long membershipId = -1L;
    private final String userId = "userId";
    private final MembershipType membershipType = MembershipType.NAVER;
    private final Integer point = 10000;


    @Order(1)
    @DisplayName("멤버쉽등록실패_이미_존재함")
    @Test
    public void MembershipRegistrationFailure_AlreadyExists() {
        // given (준비) : 어떠한 데이터가 준비되었을 때
        doReturn(Membership.builder().build()).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);

        // when (실행) : 어떠한 함수를 실행하면
        final MembershipException result = assertThrows(MembershipException.class, () -> membershipService.addMembership(userId, membershipType, point));

        // then (검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
    }

    @Order(2)
    @DisplayName("멤버쉽등록성공")
    @Test
    public void MembershipRegistrationSuccessfulTest() {
        // given (준비) : 어떠한 데이터가 준비되었을 때
        doReturn(null).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);
        doReturn(membership()).when(membershipRepository).save(any(Membership.class));

        // when (실행) : 어떠한 함수를 실행하면
        final MembershipAddResponse result = membershipService.addMembership(userId, membershipType, point);

        // then (검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getId()).isNotNull();
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);

        // verify
        verify(membershipRepository, times(1)).findByUserIdAndMembershipType(userId, membershipType);
        verify(membershipRepository, times(1)).save(any(Membership.class));
    }

    private Membership membership() {
        return Membership.builder()
                .id(-1L)
                .userId(userId)
                .point(point)
                .membershipType(MembershipType.NAVER)
                .build();
    }

    @Order(3)
    @DisplayName("멤버쉽목록조회")
    @Test
    public void MembershipListCheckTest() {
        // given (준비) : 어떠한 데이터가 준비되었을 때
        doReturn(Arrays.asList(
                Membership.builder().build(),
                Membership.builder().build(),
                Membership.builder().build()
        )).when(membershipRepository).findAllByUserId(userId);

        // when (실행) : 어떠한 함수를 실행하면
        final List<Membership> result = membershipService.getMembershipList(userId);

        // then (검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.size()).isEqualTo(3);

    }

    @Order(4)
    @Test
    @DisplayName("멤버쉽상세조회실패_존재하지않음")
    public void MembershipDetailsInquiryFailed_DoesNotExistTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        doReturn(Optional.empty()).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        final MembershipException result = assertThrows(MembershipException.class, () -> membershipService.getMembership(membershipId, userId));

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);

    }

    @Order(5)
    @Test
    @DisplayName("멤버쉽상세조회실패_본인이아님")
    public void MembershipDetailsInquiryFailed_NotYouTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        doReturn(Optional.empty()).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        final MembershipException result = assertThrows(MembershipException.class, () -> membershipService.getMembership(membershipId, "notowner"));

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);
    }

    @Order(6)
    @Test
    @DisplayName("멤버쉽상세조회성공")
    public void MembershipDetailsInquiry_SuccessfulTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        doReturn(Optional.of(membership())).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        final MembershipDetailResponse result = membershipService.getMembership(membershipId, userId);

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(result.getPoint()).isEqualTo(point);
    }

    @Order(7)
    @Test
    @DisplayName("멤버쉽삭제실패_존재하지않음")
    public void MembershipDeletionFailed_DoesNotExistTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        doReturn(Optional.empty()).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        final MembershipException result = assertThrows(MembershipException.class, () -> membershipService.removeMembership(membershipId, userId));

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);
    }

    @Order(8)
    @Test
    @DisplayName("멤버쉽삭제실패_본인이아님")
    public void MembershipDeletionFailed_NotYouTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final Membership membership = membership();
        doReturn(Optional.of(membership)).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        final MembershipException result = assertThrows(MembershipException.class, () -> membershipService.removeMembership(membershipId, "notowner"));

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
    }

    @Order(9)
    @Test
    @DisplayName("멤버쉽삭제성공")
    public void MembershipDeletionSuccessfulTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final Membership membership = membership();
        doReturn(Optional.of(membership)).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        membershipService.removeMembership(membershipId, userId);

        // then(검증) : 어떠한 결과가 나와야 한다.

    }

    @Order(10)
    @Test
    @DisplayName("멤버쉽적립실패_존재하지않음")
    public void MembershipAccumulationFailed_DoesNotExistTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        doReturn(Optional.empty()).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        final MembershipException result = assertThrows(MembershipException.class, () -> membershipService.accumulateMembershipPoint(membershipId, userId, 10000));

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);

    }

    @Order(11)
    @Test
    @DisplayName("멤버쉽적립실패_본인이아님")
    public void MembershipAccumulationFailed_NotYouTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final Membership membership = membership();
        doReturn(Optional.of(membership)).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        final MembershipException result = assertThrows(MembershipException.class, () -> membershipService.accumulateMembershipPoint(membershipId, "notowner", 10000));

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
    }

    @Order(12)
    @Test
    @DisplayName("멤버쉽적립성공")
    public void MembershipAccumulationSuccessTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final Membership membership = membership();
        doReturn(Optional.of(membership)).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        membershipService.accumulateMembershipPoint(membershipId, userId, 10000);

        // then(검증) : 어떠한 결과가 나와야 한다.

    }
}
