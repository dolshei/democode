package com.example.democode.domain.sample.simple.collection.exam03;

import java.util.ArrayList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        // ArrayList Collection 객체 생성
        List<String> list1 = new ArrayList<String>();

        // LinkedList Collection 객체 생성
        List<String> list2 = new ArrayList<String>();

        // 시작 시간과 끝 시간을 저장할 변수 선언
        long startTime;
        long endTime;

        // ArrayList Collection 에 저장하는 시간 측정
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            list1.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();
        System.out.printf("%-17s %8d ns \n", "ArrayList 걸린 시간 : ", (endTime - startTime));

        // LinkedList Collection 에 저장하는 시간 측정
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            list2.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();
        System.out.printf("%-17s %8d ns \n", "LinkedList 걸린 시간 : ", (endTime - startTime));
    }
}
