package com.example.democode.domain.sample.simple.stream.exam04;

public class Student implements Comparable<Student> {
    // Fields
    private String name;
    private int score;

    // Constructors
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Methods
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(score, o.score);
    }
}
