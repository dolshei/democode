package com.example.democode.domain.sample.simple.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NullObject {
    public static void main(String[] args) {
        // 이름이 null 인 Student 객체
        Student student = new Student(1, null);

        // 1. default
        // 값이 null 인 field 는, Json 에 포함시키지 않습니다.
        Gson gsonWithoutNull = new Gson();
        String studentJson = gsonWithoutNull.toJson(student);

        System.out.println(studentJson);

        // 2. serializeNulls 옵션
        // 값이 null 인 field 도, Json 에 포함시킵니다.
        Gson gsonWithNull = new GsonBuilder().serializeNulls().create();
        String studentJsonWithNull = gsonWithNull.toJson(student);

        System.out.println(studentJsonWithNull);
    }
}
