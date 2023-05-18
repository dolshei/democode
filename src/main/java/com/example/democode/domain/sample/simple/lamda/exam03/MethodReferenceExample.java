package com.example.democode.domain.sample.simple.lamda.exam03;

public class MethodReferenceExample {
    public static void main(String[] args) {
        Person person = new Person();
        person.ordering(String::compareToIgnoreCase);
    }
}
