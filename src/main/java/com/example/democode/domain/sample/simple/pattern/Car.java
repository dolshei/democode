package com.example.democode.domain.sample.simple.pattern;

public class Car {
    // Fields
    private String id;
    private String name;

    // Constructor
    public Car(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Method
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
