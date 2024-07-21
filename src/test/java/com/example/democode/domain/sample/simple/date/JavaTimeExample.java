package com.example.democode.domain.sample.simple.date;

import java.time.LocalDate;

public class JavaTimeExample {
    public static void main(String[] args) {
        LocalDate currDate = LocalDate.now();
        //LocalDate targetDate = LocalDate.of(int year, int month, int dayOfMonth);

        System.out.println(currDate);
    }
}
