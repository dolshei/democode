package com.example.democode.domain.membership.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class RatePointServiceTest {
    @InjectMocks
    private RatePointService ratePointService;

    @Test
    @DisplayName("10000원의적립은100원")
    @Order(1)
    public void Accumulate100PointsTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final int price = 10000;

        // when(실행) : 어떠한 함수를 실행하면
        final int result = ratePointService.calculateAmount(price);

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result).isEqualTo(100);
    }

    @Test
    @DisplayName("20000원의적립은200원")
    @Order(2)
    public void Accumulate200PointsTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final int price = 20000;

        // when(실행) : 어떠한 함수를 실행하면
        final int result = ratePointService.calculateAmount(price);

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result).isEqualTo(200);

    }

    @Test
    @DisplayName("30000원의적립은300원")
    @Order(3)
    public void Accumulate300PointsTest() {
        // given(준비) : 어떠한 데이터가 준비 되었을 때
        final int price = 30000;

        // when(실행) : 어떠한 함수를 실행하면
        final int result = ratePointService.calculateAmount(price);

        // then(검증) : 어떠한 결과가 나와야 한다.
        assertThat(result).isEqualTo(300);

    }
}
