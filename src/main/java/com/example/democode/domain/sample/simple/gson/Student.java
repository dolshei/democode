package com.example.democode.domain.sample.simple.gson;

import com.google.gson.annotations.Expose;

public class Student {
    //private transient int id;
    private int id;

    private String name;


    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + "]";
    }
}
