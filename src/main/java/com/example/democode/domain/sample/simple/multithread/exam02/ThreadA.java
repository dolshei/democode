package com.example.democode.domain.sample.simple.multithread.exam02;

import com.example.democode.domain.sample.simple.multithread.WorkThread;

public class ThreadA extends Thread {
    // Fields
    private WorkObject workObject;

    // Constructors
    public ThreadA(WorkObject workObject) {
        setName("ThreadA");
        this.workObject = workObject;
    }

    // Methods
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            workObject.methodA();
        }
    }
}
