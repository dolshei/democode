package com.example.democode.domain.sample.simple.multithread;

public class WorkThread extends Thread {
    // Fields
    public boolean work = true;

    // Constructors
    public WorkThread(String name) {
        setName(name);
    }

    // Methods
    @Override
    public void run() {
        while (true) {
            if (work) {
                System.out.println(getName() + " : 작업처리");
            } else {
                Thread.yield();
            }
        }
    }
}
