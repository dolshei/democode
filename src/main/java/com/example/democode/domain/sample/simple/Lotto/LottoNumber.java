package com.example.democode.domain.sample.simple.Lotto;

import java.util.Arrays;
import java.util.Random;

public class LottoNumber {
    public static void main(String[] args) {
        //check 배열
        //1~45에서 뽑은 것은 1, 안뽑은 것은 0으로 체크해줌
        //1~45 난수 뽑고 저장 로또 같이 6개 뽑음
        Random r = new Random();

        int lotto[] = new int[6];
        int check[] = new int[46];

        System.out.println("===========================\n");
        for (int k = 'A'; k <= 'E'; k++) {
            System.out.print((char) k + " 자 동  ");
            for (int i = 0; i < lotto.length; i++) {
                lotto[i] = r.nextInt(45) + 1;
                //lotto[i] 번째에 해당하는
                //안뽑은 것은 0으로 체크해줌 뽑은 것은 0으로 체크해서 해당 i번째 인덱스를 다시 뽑음
                if (check[lotto[i]] == 0) {
                    check[lotto[i]] = 1;
                } else
                    i--;
            }
            Arrays.sort(lotto); //추첨된 6자리 숫자를 크기 순으로 오름차순으로 정렬
            for (int i = 0; i < lotto.length; i++) {
                if (lotto[i] < 10) {
                    System.out.print("0" + lotto[i] + " ");
                } else {
                    System.out.print(lotto[i] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("===========================");


    }
}
