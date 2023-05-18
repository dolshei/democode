package com.example.democode.domain.sample.simple.stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest01 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Lee", "Park", "Kim");

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("-------------------------");

        list.forEach(System.out::println);
    }


}
