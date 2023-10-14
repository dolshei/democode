package com.example.democode.domain.sample.simple.gson;

import com.google.gson.Gson;

public class ObjectToJson {
    public static void main(String[] args) {

        // Student 객체 생성
        Student student = new Student(1, "duri");

        // Gson 객체 생성
        Gson gson = new Gson();

        // Student 객체 -> Json 문자열
        String studentJson = gson.toJson(student);

        System.out.println(studentJson);
    }
}
