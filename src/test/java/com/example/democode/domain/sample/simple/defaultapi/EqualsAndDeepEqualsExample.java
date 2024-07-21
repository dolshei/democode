package com.example.democode.domain.sample.simple.defaultapi;

import java.util.Objects;

public class EqualsAndDeepEqualsExample {
    public static void main(String[] args) {
        Integer o1 = 1000;
        Integer o2 = 1000;

        System.out.println(Objects.equals(o1, o2));
        System.out.println(Objects.equals(o1, null));
        System.out.println(Objects.equals(null, o2));
        System.out.println(Objects.equals(null, null));
        System.out.println(Objects.deepEquals(o1, o2) + "\n");
    }
}
