package com.example.democode.domain.sample.simple.datainout.exam02;

import java.io.*;
import java.util.Arrays;

public class ObjectInputOutputStreamExample {
    public static void main(String[] args) throws Exception {
        // FileOutputStream 에 OjbectOutputStream 보조 스트림 연결
        FileOutputStream fileOutputStream = new FileOutputStream("C:/Temp/object.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        // 객체 생성
        Member m1 = new Member("fall", "단풍이");
        Product p1 = new Product("노트북", 1500000);
        int[] arr1 = {1, 2, 3};

        // 객체를 역직렬화해서 파일에 저장
        objectOutputStream.writeObject(m1);
        objectOutputStream.writeObject(p1);
        objectOutputStream.writeObject(arr1);

        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();

        // FileInputStream 에 ObjectInputStream 보조 스트림 연결
        FileInputStream fileInputStream = new FileInputStream("C:/Temp/object.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        // 파일을 읽고 역직렬화해서 객체로 복원
        Member m2 = (Member) objectInputStream.readObject();
        Product p2 = (Product) objectInputStream.readObject();
        int[] arr2 = (int[]) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        // 복원된 객체 내용 확인
        System.out.println(m2);
        System.out.println(p2);
        System.out.println(Arrays.toString(arr2));
    }
}
