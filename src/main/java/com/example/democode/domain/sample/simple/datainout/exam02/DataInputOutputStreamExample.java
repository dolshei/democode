package com.example.democode.domain.sample.simple.datainout.exam02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataInputOutputStreamExample {
    public static void main(String[] args) throws Exception {
        //DataOutputStream 생성
        FileOutputStream fileOutputStream = new FileOutputStream("C:/Temp/primitive.db");
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

        // 기본 타입 출력
        dataOutputStream.writeUTF("홍길동");
        dataOutputStream.writeDouble(95.5);
        dataOutputStream.writeInt(1);

        dataOutputStream.writeUTF("감자바");
        dataOutputStream.writeDouble(90.3);
        dataOutputStream.writeInt(2);

        dataOutputStream.flush();
        dataOutputStream.close();
        fileOutputStream.close();

        // DataInputStream 생성
        FileInputStream fileInputStream = new FileInputStream("C:/Temp/primitive.db");
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);

        // 기본 타입 입력
        for (int i = 0; i < 2; i++) {
            String name = dataInputStream.readUTF();
            double score = dataInputStream.readDouble();
            int order = dataInputStream.readInt();
            System.out.println(name + " : " + score + " : " + order);
        }

        dataInputStream.close();
        fileInputStream.close();
    }
}
