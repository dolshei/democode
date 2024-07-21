package com.example.democode.domain.membership.controller;

import com.example.democode.domain.membership.dto.MembershipRequest;
import com.example.democode.domain.membership.model.MembershipType;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MembershipControllerTest {

    @InjectMocks
    private MembershipController membershipController;

    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    public void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(membershipController).build();
    }

    @DisplayName("mockMvc 가 Null 이 아님")
    @Test
    public void mockMvcIsNotNullTest() throws Exception {
        assertThat(membershipController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("멤버쉽등록실패_사용자식별값이헤더에없음")
    @Test
    public void UserIdentificationValueIsNotInHeaderTest() throws Exception {
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
}
