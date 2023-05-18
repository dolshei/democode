package com.example.democode.domain.sample.simple.generic.exam02;

public class Applicant<T> {
    public T kind;

    public Applicant(T kind) {
        this.kind = kind;
    }
}
