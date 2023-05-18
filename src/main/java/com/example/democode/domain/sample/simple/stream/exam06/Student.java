package com.example.democode.domain.sample.simple.stream.exam06;

public class Student {
    // Fields
    private String name;
    private String sex;
    private int score;

    // Constructors
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Student(String name, String sex, int score) {
        this.name = name;
        this.sex = sex;
        this.score = score;
    }

    // Methods
    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getScore() {
        return score;
    }
}
