package com.example.democode.membership.service;

import com.example.democode.membership.entity.Membership;
import com.example.democode.membership.entity.MembershipErrorResult;
import com.example.democode.membership.entity.MembershipType;
import com.example.democode.membership.exception.MembershipException;
import com.example.democode.membership.repository.MembershipRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    // MembershipService는 테스트 대상이므로 의존성이 주입되는 어노테이션인 @InjectMocks를 붙여주었고
    @InjectMocks
    private MembershipService target;

    // MembershipRepository가 의존성이 있는 클래스이므로 가짜 객체 생성을 도와주는 @Mock를 붙여주었다.
    @Mock
    private MembershipRepository membershipRepository;

    private final String userId = "userId";
    private final MembershipType membershipType = MembershipType.NAVER;
    private final Integer point = 10000;

    @DisplayName("멤버쉽등록실패_이미_존재함")
    @Test
    public void MembershipRegistrationFailureAlreadyExists() {
        // given (준비) : 어떠한 데이터가 준비되었을 때
        doReturn(Membership.builder().build()).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);

        // when (실행) : 어떠한 함수를 실행하면
        final MembershipException result = assertThrows(MembershipException.class, () -> target.addMembership(userId, membershipType, point));

        // then (검증) : 어떠한 결과가 나와야 한다.
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);

    }
}
