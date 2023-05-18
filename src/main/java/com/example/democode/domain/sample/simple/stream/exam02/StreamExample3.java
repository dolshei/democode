package com.example.democode.domain.sample.simple.stream.exam02;

import java.util.stream.IntStream;

public class StreamExample3 {
    public static int sum;

    public static void main(String[] args) {
        IntStream stream = IntStream.rangeClosed(1, 100);
        stream.forEach(value -> sum += value);
        System.out.println("총합 : " + sum);
    }
}
