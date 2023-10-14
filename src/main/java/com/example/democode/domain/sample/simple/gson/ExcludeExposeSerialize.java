package com.example.democode.domain.sample.simple.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExcludeExposeSerialize {
    public static void main(String[] args) {
        // Student 객체 생성
        Teacher teacher = new Teacher(1, "duri", "CS");

        // Gson 객체 생성
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        // Student 객체 -> Json 문자열
        String teacherJson = gson.toJson(teacher);

        // Json 문자열 출력
        System.out.println(teacherJson);
    }
}
