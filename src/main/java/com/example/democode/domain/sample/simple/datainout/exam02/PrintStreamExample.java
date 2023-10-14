package com.example.democode.domain.sample.simple.datainout.exam02;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamExample {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("C:/Temp/printstream.txt");
        PrintStream printStream = new PrintStream(fileOutputStream);

        printStream.print("마치 ");
        printStream.println("프린터가 출력하는 것처럼 ");
        printStream.println("데이터를 출력합니다.");
        printStream.printf("| %6d | %-10s | %10s | \n", 1, "홍길동", "도적");
        printStream.printf("| %6d | %-10s | %10s | \n", 2, "감자바", "학생");

        printStream.flush();
        printStream.close();
    }
}
