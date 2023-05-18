package com.example.democode.domain.sample.simple.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PrettyPrinting {
    public static void main(String[] args) {
        Student student = new Student(1, "duri");

        // PrettyPrinting 옵션을 추가하여 Gson 객체 생성
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String studentJson = gson.toJson(student);

        System.out.println(studentJson);
    }
}
