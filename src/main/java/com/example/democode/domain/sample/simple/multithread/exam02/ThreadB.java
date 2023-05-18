package com.example.democode.domain.sample.simple.multithread.exam02;

public class ThreadB extends Thread {
    // Fields
    private WorkObject workObject;

    // Constructors
    public ThreadB(WorkObject workObject) {
        setName("ThreadB");
        this.workObject = workObject;
    }

    // Methods
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            workObject.methodB();
        }
    }
}
