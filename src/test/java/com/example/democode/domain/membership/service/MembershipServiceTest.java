package com.example.democode.domain.membership.service;

import com.example.democode.domain.membership.dto.MembershipAddResponse;
import com.example.democode.domain.membership.dto.MembershipDetailResponse;
import com.example.democode.domain.membership.entity.Membership;
import com.example.democode.domain.membership.exception.MembershipException;
import com.example.democode.domain.membership.model.MembershipErrorResult;
import com.example.democode.domain.membership.model.MembershipType;
import com.example.democode.domain.membership.repository.MembershipRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
public class MembershipServiceTest {

    private static final Logger log = LoggerFactory.getLogger(MembershipServiceTest.class);
    // MembershipService는 테스트 대상이므로 의존성이 주입되는 어노테이션인 @InjectMocks를 붙여주었고
    @InjectMocks
    private MembershipService membershipService;

    // MembershipRepository가 의존성이 있는 클래스이므로 가짜 객체 생성을 도와주는 @Mock를 붙여주었다.
    @Mock
    private MembershipRepository membershipRepository;

    private final Long membershipId = 1L;
    private final String userId = "userId";
    private final MembershipType membershipType = MembershipType.NAVER;
    private final Integer point = 10000;


    @DisplayName("멤버쉽등록실패_이미_존재함")
    @Test
    public void MembershipRegistrationFailureAlreadyExists() {
        // given (준비) : 어떠한 데이터가 준비되었을 때
        doReturn(Membership.builder().build()).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);

        // when (실행) : 어떠한 함수를 실행하면
        final MembershipException result = assertThrows(MembershipException.class, () -> membershipService.addMembership(userId, membershipType, point));

        // then (검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
    }

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

    @Test
    @DisplayName("멤버쉽상세조회실패_존재하지않음")
    public void MembershipDetailsInquiryFailedDoesNotExistTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        doReturn(Optional.empty()).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        final MembershipException result = assertThrows(MembershipException.class, () -> membershipService.getMembership(membershipId, userId));

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);

    }

    @Test
    @DisplayName("멤버쉽상세조회실패_본인이아님")
    public void MembershipDetailsInquiryFailedNotYouTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        doReturn(Optional.empty()).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        final MembershipException result = assertThrows(MembershipException.class, () -> membershipService.getMembership(membershipId, "notowner"));

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);
    }

    @Test
    @DisplayName("멤버쉽상세조회성공")
    public void MembershipDetailsInquirySuccessfulTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        doReturn(Optional.of(membership())).when(membershipRepository).findById(membershipId);

        // when(실행) : 어떠한 함수를 실행하면
        final MembershipDetailResponse result = membershipService.getMembership(membershipId, userId);

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(result.getPoint()).isEqualTo(point);
    }
}
