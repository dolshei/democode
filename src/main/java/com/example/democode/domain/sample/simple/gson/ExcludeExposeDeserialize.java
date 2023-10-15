package com.example.democode.domain.sample.simple.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExcludeExposeDeserialize {
    public static void main(String[] args) {
        // Json 문자열
        String jsonStr = "{\"id\":1,\"name\":\"Anna\", \"major\":\"CS\"}";

        // Gson 객체 생성
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        // Json 문자열 -> Student 객체
        Teacher teacher = gson.fromJson(jsonStr, Teacher.class);

        // Student 객체 toString() 출력
        System.out.println(teacher);
    }
}
