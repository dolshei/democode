package com.example.democode.domain.sample.simple.gson;

import com.google.gson.Gson;

public class JsonToObject {

    public static void main(String[] args) {
        String jsonStr = "{\"id\":1, \"name\":\"duri\"}";

        Gson gson = new Gson();

        // Json 문자열 -> Student 객체
        Student student = gson.fromJson(jsonStr, Student.class);

        System.out.println(student);
    }
}
