package com.example.democode.domain.sample.simple;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class StreamTest {

    @DisplayName("Stream Test")
    @Test
    public void streamTest01() {
        List<String> list = Arrays.asList("Lee", "Park", "Kim");

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("-------------------------");

        list.forEach(System.out::println);
    }
}
