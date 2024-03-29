package com.example.democode.domain.sample.simple.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Gson 라이브러리 사용법 및 예제
 * https://hianna.tistory.com/629
 */
public class CreateGson {
    public static void main(String[] args) {
        Gson gson1 = new Gson();

        Gson gson2 = new GsonBuilder().create();
        Gson gson3 = new GsonBuilder().setPrettyPrinting().create();
    }
}
