package com.example.democode.domain.sample.simple.stream.exam06;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class ParallelExample {
    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < 100000000; i++) {
            scores.add(random.nextInt(101));
        }

        double avg = 0.0;
        long stratTime = 0;
        long endTime = 0;
        long time = 0;
        long streamTime = 0;
        long parallelStreamTime = 0;

        Stream<Integer> stream = scores.stream();
        stratTime = System.nanoTime();
        avg = stream
                .mapToInt(i -> i.intValue())
                .average()
                .getAsDouble();
        endTime = System.nanoTime();
        streamTime = endTime - stratTime;
        System.out.println("avg : " + avg + ", 일반 스트림 처리 시간 : " + streamTime + "ns");

        Stream<Integer> parallelStream = scores.parallelStream();
        stratTime = System.nanoTime();
        avg = parallelStream
                .mapToInt(i -> i.intValue())
                .average()
                .getAsDouble();
        endTime = System.nanoTime();
        parallelStreamTime = endTime - stratTime;
        System.out.println("avg : " + avg + ", 병렬 처리 스트림 처리 시간 : " + parallelStreamTime + "ns");

        time = streamTime - parallelStreamTime;

        System.out.println("처리 시간 차이 : " + time + "ns");
    }
}
