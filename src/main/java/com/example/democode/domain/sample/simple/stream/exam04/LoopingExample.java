package com.example.democode.domain.sample.simple.stream.exam04;

import java.util.Arrays;

public class LoopingExample {
    public static void main(String[] args) {
        int[] intArr = {1, 2, 3, 4, 5};

        // 잘못 작성한 경우
        Arrays.stream(intArr)
                .filter(a -> a % 2 == 0)
                .peek(n -> System.out.println(n));

        // 중간 처리 메소드 peek() 을 이용해서 반복 처리
        int total = Arrays.stream(intArr)
                .filter(a -> a % 2 == 0)
                .peek(n -> System.out.println(n))
                .sum();
        System.out.println("총합 : " + total);

        System.out.println();

        // 최종 처리 메소드 forEach() 를 이용해서 반복 처리
        Arrays.stream(intArr)
                .filter(a -> a % 2 == 0)
                .forEach(n -> System.out.println(n));
    }
}
