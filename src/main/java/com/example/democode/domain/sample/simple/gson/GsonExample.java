package com.example.democode.domain.sample.simple.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GsonExample {
    public static void main(String[] args) {
        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "young");
        jsonObject.addProperty("id", 1);

        String jsonString = gson.toJson(jsonObject);

        System.out.println(jsonString);
    }
}
