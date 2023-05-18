package com.example.democode.domain.sample.simple.multithread.exam01;

public class PrintThread extends Thread {
    private boolean stop;

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        while (!stop) {
            System.out.println("실행중");
        }
        System.out.println("리소스 정리");
        System.out.println("실행 종료");
    }
}
