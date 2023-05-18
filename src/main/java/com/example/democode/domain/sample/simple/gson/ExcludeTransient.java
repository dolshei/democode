package com.example.democode.domain.sample.simple.gson;

import com.google.gson.Gson;

public class ExcludeTransient {
    public static void main(String[] args) {

        Student student = new Student(1, "duri");

        Gson gson = new Gson();

        String studentJson = gson.toJson(student);

        System.out.println(studentJson);
    }
}
