package com.example.democode.domain.sample.simple.wrapper;

public class AutoBoxingUnBoxingExample {
    public static void main(String[] args) {
        // Auto Boxing
        Integer obj = 100;
        System.out.println("value : " + obj.intValue());

        // 대입 시 Auto Boxing
        int value = obj;
        System.out.println("value : " + value);

        // 연산 시 Auto Unboxing
        int result = obj + 100;
        int result2 = obj.intValue() + 100;
        System.out.println("result : " + result);
        System.out.println("result2 : " + result2);
    }
}
