package com.example.democode.domain.sample.simple.datainout.exam02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyExample {
    public static void main(String[] args) throws Exception {
        String originalFileName = "C:/Temp/atb.jpg";
        String targetFileName = "C:/Temp/test/atb3.jpg";

        InputStream is = new FileInputStream(originalFileName);
        OutputStream os = new FileOutputStream(targetFileName);

    /*
        byte[] data = new byte[1024];
        while (true) {
            int num = is.read(data);
            if (num == -1) break;

            os.write(data, 0, num);
        }
    */
        // Java 9 부터 위의 주석줄 내용이 아래 한줄로 대체 가능
        is.transferTo(os);

        os.flush();
        os.close();
        is.close();

        System.out.println("복사가 되었습니다.");
    }
}
