package com.example.democode.domain.sample.simple.stream.exam02;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class StreamExample4 {
    public static void main(String[] args) throws Exception {
    /*
        Path path = Paths.get(StreamExample4.class.getResource("E:\\StudySpace2\\IdeaProjects\\democode\\src\\main\\java\\com\\example\\democode\\domain\\sample\\simple\\stream\\exam02\\data.txt").toURI());
        Stream<String> stream = Files.lines(path, Charset.defaultCharset());
        stream.forEach(line -> System.out.println(line));
        stream.close();
    */

    /*    // 방법1
        try {
            List<String> allLines = Files.readAllLines(Paths.get("E:\\StudySpace2\\IdeaProjects\\democode\\src\\main\\java\\com\\example\\democode\\domain\\sample\\simple\\stream\\exam02\\data.txt"));

            for (String line : allLines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    */

    /*    // 방법2
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("E:\\StudySpace2\\IdeaProjects\\democode\\src\\main\\java\\com\\example\\democode\\domain\\sample\\simple\\stream\\exam02\\data.txt"));
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                // read line next
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    */

        // 방법3
        try {
            Scanner scanner = new Scanner(new File("E:\\StudySpace2\\IdeaProjects\\democode\\src\\main\\java\\com\\example\\democode\\domain\\sample\\simple\\stream\\exam02\\data.txt"));

            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
