package com.example.democode.domain.sample.simple.array;

public class Member implements Comparable<Member> {
    String name;

    Member(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Member o) {
        return name.compareTo(o.name);
    }
}
