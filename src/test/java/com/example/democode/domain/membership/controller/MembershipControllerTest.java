package com.example.democode.domain.membership.controller;

import com.example.democode.domain.membership.dto.MembershipAddResponse;
import com.example.democode.domain.membership.dto.MembershipDetailResponse;
import com.example.democode.domain.membership.dto.MembershipRequest;
import com.example.democode.domain.membership.exception.GlobalExceptionHandler;
import com.example.democode.domain.membership.exception.MembershipException;
import com.example.democode.domain.membership.model.MembershipErrorResult;
import com.example.democode.domain.membership.model.MembershipType;
import com.example.democode.domain.membership.service.MembershipService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.example.democode.domain.membership.model.MembershipConstants.USER_ID_HEADER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MembershipControllerTest {

    @InjectMocks
    private MembershipController membershipController;

    @Mock
    private MembershipService membershipService;

    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    public void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(membershipController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @DisplayName("mockMvc 가 Null 이 아님")
    @Test
    public void mockMvcIsNotNullTest() {
        assertThat(membershipController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("멤버쉽등록실패_사용자식별값이헤더에없음")
    @Test
    public void userIdentificationValueIsNotInHeaderTest() throws Exception {
        // given (준비) : 어떠한 데이터가 준비되었을 때
        final String url = "/api/v1/memberships";

        // when (실행) : 어떠한 함수를 실행하면
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(membershipRequest(1000, MembershipType.NAVER)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then (검증) : 어떠한 결과가 나와야 한다.
        resultActions.andExpect(status().isBadRequest());

    }

    private MembershipRequest membershipRequest(final Integer point, MembershipType membershipType) {
        return MembershipRequest.builder()
                .point(point)
                .membershipType(membershipType)
                .build();
    }


    // 멤버쉽 등록 실패 3가지 테스트 코드 리팩토링
    // 멤버쉽등록실패_잘못된파라미터 로 3가지 케이스를 1개의 테스트로 만들고 파라미터만 다르게 하여 중복을 제거
    @Disabled
    @Test
    @DisplayName("멤버쉽등록실패_포인트가_NULL")
    public void membershipRegistrationFailedPointsNULLTest() throws Exception {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final String url = "/api/v1/memberships";

        // when(실행) : 어떠한 함수를 실행하면
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .header(USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(null, MembershipType.NAVER)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then(검증) : 어떠한 결과가 나와야 한다.
        resultActions.andExpect(status().isBadRequest());
    }

    @Disabled
    @Test
    @DisplayName("멤버쉽등록실패_포인트가음수")
    public void membershipRegistrationFailurePointFalseNegativeTest() throws Exception {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final String url = "/api/v1/memberships";

        // when(실행) : 어떠한 함수를 실행하면
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .header(USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(-1, MembershipType.NAVER)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then(검증) : 어떠한 결과가 나와야 한다.
        resultActions.andExpect(status().isBadRequest());
    }

    @Disabled
    @Test
    @DisplayName("멤버쉽등록실패_멤버쉽종류가_NULL")
    public void membershipRegistrationFailedMembershipTypeNullTest() throws Exception {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final String url = "/api/v1/memberships";

        // when(실행) : 어떠한 함수를 실행하면
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .header(USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(10000, null)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then(검증) : 어떠한 결과가 나와야 한다.
        resultActions.andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource("invalidMembershipAddParameter")
    @DisplayName("멤버쉽등록실패_잘못된파라미터")
    public void membershipRegistrationFailureInvalidParameterTest(final Integer point, final MembershipType membershipType) throws Exception {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final String url = "/api/v1/memberships";

        // when(실행) : 어떠한 함수를 실행하면
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .header(USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(point, membershipType)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then(검증) : 어떠한 결과가 나와야 한다.
        resultActions.andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> invalidMembershipAddParameter() {
        return Stream.of(
                Arguments.of(null, MembershipType.NAVER),
                Arguments.of(-1, MembershipType.NAVER),
                Arguments.of(10000, null)
        );
    }

    @Test
    @DisplayName("멤버쉽등록실패_MemberService 에서 에러 Throw")
    public void membershipRegistrationFailedMemberServiceErrorThrowTest() throws Exception {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final String url = "/api/v1/memberships";
        doThrow(new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER))
                .when(membershipService)
                .addMembership("12345", MembershipType.NAVER, 10000);

        // when(실행) : 어떠한 함수를 실행하면
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .header(USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(10000, MembershipType.NAVER)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then(검증) : 어떠한 결과가 나와야 한다.
        resultActions.andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("멤버쉽등록성공")
    public void membershipRegistrationSuccessfulTest() throws Exception {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final String url = "/api/v1/memberships";
        final MembershipAddResponse membershipResponse = MembershipAddResponse.builder()
                .id(-1L)
                .membershipType(MembershipType.NAVER)
                .build();

        doReturn(membershipResponse).when(membershipService).addMembership("12345", MembershipType.NAVER, 10000);

        // when(실행) : 어떠한 함수를 실행하면
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .header(USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(10000, MembershipType.NAVER)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then(검증) : 어떠한 결과가 나와야 한다.
        resultActions.andExpect(status().isCreated());

        final MembershipAddResponse response = gson.fromJson(resultActions.andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8), MembershipAddResponse.class);

        assertThat(response.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(response.getId()).isNotNull();
    }

    @Test
    @DisplayName("멤버쉽목록_조회실패_사용자식별값이_헤더에_없음")
    public void MembershipListSearchFailedUserIdentificationValueIsNotInHeaderTest() throws Exception {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final String url = "/api/v1/memberships";

        // when(실행) : 어떠한 함수를 실행하면
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        );

        // then(검증) : 어떠한 결과가 나와야 한다.
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("멤버쉽목록_조회성공")
    public void MembershipListSearchSuccessTest() throws Exception {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final String url = "/api/v1/memberships";
        doReturn(Arrays.asList(
                MembershipDetailResponse.builder().build(),
                MembershipDetailResponse.builder().build(),
                MembershipDetailResponse.builder().build()
        )).when(membershipService).getMembershipList("12345");

        // when(실행) : 어떠한 함수를 실행하면
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url).header(USER_ID_HEADER, "12345")
        );

        // then(검증) : 어떠한 결과가 나와야 한다.
        resultActions.andExpect(status().isOk());

    }
}
