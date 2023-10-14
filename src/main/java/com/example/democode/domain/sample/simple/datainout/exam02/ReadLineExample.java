package com.example.democode.domain.sample.simple.datainout.exam02;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadLineExample {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/com/example/democode/domain/sample/simple/datainout/exam02/ReadLineExample.java"));

        int lineNo = 1;
        while (true) {
            String str = bufferedReader.readLine();
            if (str == null) break;
            System.out.println(lineNo + "\t" + str);
            lineNo++;
        }

        bufferedReader.close();
    }
}
