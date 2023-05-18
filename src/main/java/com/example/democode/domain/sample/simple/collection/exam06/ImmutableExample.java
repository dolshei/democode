package com.example.democode.domain.sample.simple.collection.exam06;

import java.util.*;

public class ImmutableExample {
    public static void main(String[] args) {
        // List 불변 컬렉션 생성
        List<String> immutableList1 = List.of("A", "B", "C");

        // Set 불변 컬렉션 생성
        Set<String> immutableSet1 = Set.of("A", "B", "C");

        // Map 불변 컬렉션 생성
        Map<Integer, String> immutalbeMap1 = Map.of(
                1, "A",
                2, "B",
                3, "C"
        );

        // List 컬렉션을 불변 컬렉션으로 복사
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        List<String> immutableList2 = List.copyOf(list);

        System.out.println(immutableList2);

        // Set 컬렉션을 불변 컬렉션으로 복사
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        Set<String> immutableSet2 = Set.copyOf(set);

        System.out.println(immutableSet2);

        // Map 컬렉션을 불변 컬렉션으로 복사
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        Map<Integer, String> immutableMap2 = Map.copyOf(map);

        System.out.println(immutableMap2);

        // 배열로부터 List 불변 컬렉션 생성
        String[] arr = {"A", "B", "C"};
        List<String> immutableList3 = Arrays.asList(arr);

        System.out.println(immutableList3);

    }
}
