package com.example.democode.domain.sample.simple.lamda.exam02;

public class MethodReferenceExample {
    public static void main(String[] args) {
        Person person = new Person();

        // 정적 메소드일 경우 람다식
        person.action(Computer::staticMethod);

        // 인스턴스 메소드일 경우 람다식
        Computer computer = new Computer();
        person.action(computer::instanceMethod);
    }
}
