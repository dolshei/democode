package com.example.democode.domain.sample.simple.pattern;

import com.example.democode.domain.sample.simple.inheritance.A;

public class D extends A {
    private A a;
    // Fields

    // Constructors
    public D() {
        super();
    }

    // Methods
    public void method1() {
        this.field = "value";
        this.method();
    }
}
