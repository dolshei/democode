package com.example.democode.domain.sample.simple.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GsonExample {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Json key, value 추가
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "young");
        jsonObject.addProperty("id", 1);

        // JsonObject 를 Json 문자열 로 변환
        String jsonString = gson.toJson(jsonObject);

        System.out.println(jsonString);
    }
}
