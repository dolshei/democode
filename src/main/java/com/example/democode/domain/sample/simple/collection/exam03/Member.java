package com.example.democode.domain.sample.simple.collection.exam03;

public class Member {
    // Fields
    public String name;
    public int age;

    // Constructors
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Methods
    // hashCode 재정의 (name 과 age 값이 같으면 동일한 hashCode 가 리턴됨)
    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }

    // equals 재정의
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member target) {
            return target.name.equals(name) && (target.age == age);
        } else {
            return false;
        }
    }
}
