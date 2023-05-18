package com.example.democode.domain.sample.simple.multithread;

public class JoinExample {
    public static void main(String[] args) {
        SumThread sumThread = new SumThread();
        sumThread.start();

        try {
            sumThread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("1 ~ 100 합 : " + sumThread.getSum());
    }
}
