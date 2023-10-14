package com.example.democode.domain.sample.simple.gson;

import com.google.gson.Gson;

import java.util.Map;

public class JsonToMap {
    public static void main(String[] args) {

        // Json 문자열
        String jsonStr = "{\"id\":\"1\",\"name\":\"duri\"}";

        // Gson 객체 생성
        Gson gson = new Gson();

        // Json 문자열 -> Map
        Map<String, Object> map = gson.fromJson(jsonStr, Map.class);

        // Map 출력
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
