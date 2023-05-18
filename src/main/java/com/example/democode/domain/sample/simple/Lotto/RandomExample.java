package com.example.democode.domain.sample.simple.Lotto;

import java.util.Arrays;
import java.util.Random;

public class RandomExample {
    public static void main(String[] args) {
        // 선택 번호
        int[] selectNumber = new int[6];        // 선택 번호 6개가 저장될 배열 (자동)
        Random random = new Random(3);      // 선택 번호를 얻기 위한 Random 객체 생성
        System.out.println("선택 번호 : ");
        for (int i = 0; i < 6; i++) {
            selectNumber[i] = random.nextInt(45) + 1;       // 선택 번호를 얻어 배열에 저장
            System.out.println(selectNumber[i] + " ");
        }
        System.out.println();

        // 당첨번호
        int[] winningNumber = new int[6];       // 당첨 번호 6개가 저장될 배열
        random = new Random(3);             // 당첨 번호를 얻기 위한 Random 객체 생성
        System.out.println("당첨 번호 : ");
        for (int i = 0; i < 6; i++) {
            winningNumber[i] = random.nextInt(45) + 1;      // 당첨 번호를 얻어 배열에 저장
            System.out.println(winningNumber[i] + " ");
        }
        System.out.println();

        // 당첨 여부 확인하기
        Arrays.sort(selectNumber);      // 오름차순 정렬
        Arrays.sort(winningNumber);
        boolean result = Arrays.equals(selectNumber, winningNumber);    // 배열 항목값 비교
        System.out.println("당첨 여부 : ");
        if (result) {
            System.out.println("1등 당첨!!");
        } else {
            System.out.println("꽝!");
        }
    }
}
