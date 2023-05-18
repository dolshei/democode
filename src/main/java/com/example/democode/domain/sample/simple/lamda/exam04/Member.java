package com.example.democode.domain.sample.simple.lamda.exam04;

public class Member {
    // Fields
    private String id;
    private String name;

    // Constructors

    public Member(String id) {
        this.id = id;
        System.out.println("Member(String id)");
    }

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("Member(String id, String name)");
    }

    // Methods
    @Override
    public String toString() {
        String info = "{id: " + id + ", name: " +name +"}";
        return info;
    }
}
