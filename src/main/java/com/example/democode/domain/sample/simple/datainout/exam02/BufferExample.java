package com.example.democode.domain.sample.simple.datainout.exam02;

import java.io.*;

public class BufferExample {
    public static void main(String[] args) throws Exception {
        // 입출력 스트림 생성
        String originalFilePath1 = BufferExample.class.getResource("C:/Temp/test/atb2.jpg").getPath();
        String targetFilePath1 = "C:/Temp/targetFile1.jpg";

        FileInputStream fileInputStream = new FileInputStream(originalFilePath1);
        FileOutputStream fileOutputStream = new FileOutputStream(targetFilePath1);

        // 입출력 스트림 + 버퍼 스트림 생성
        String originalFilePath2 = BufferExample.class.getResource("C:/Temp/test/atb3.jpg").getPath();
        String targetFilePath2 = "C:/Temp/targetFile2.jpg";

        FileInputStream fileInputStream2 = new FileInputStream(originalFilePath2);
        FileOutputStream fileOutputStream2 = new FileOutputStream(targetFilePath2);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream2);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream2);

        // 버퍼를 사용하지 않고 복사
        long nonBufferTime = copy(fileInputStream, fileOutputStream);
        System.out.println("버퍼 미사용:\t" + nonBufferTime + "ns");

        // 버퍼를 사용하고 복사
        long bufferTime = copy(bufferedInputStream, bufferedOutputStream);
        System.out.println("버퍼 사용 :\t" + bufferTime + "ns");

        fileInputStream.close();
        fileOutputStream.close();
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

    public static long copy(InputStream is, OutputStream os) throws Exception {
        // 시작 시간 저장
        long start = System.nanoTime();

        // 1 바이트를 읽고 1 바이트를 출력
        while (true) {
            int data = is.read();
            if (data == -1) break;
            os.write(data);
        }
        os.flush();

        // 끝 시간 저장
        long end = System.nanoTime();

        // 복사 시간 리턴
        return (end - start);
    }
}
