package com.example.democode.domain.sample.simple.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionExample {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // JDBC Driver 등록
            Class.forName("org.mariadb.jdbc.Driver");

            // 연결하기
            connection = DriverManager.getConnection("jdbc: mariadb://localhost:3306/thisisjava", "root", "6h5g4f#D@S!A");

            System.out.println("연결 성공");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    // 연결 끊기
                    connection.close();
                    System.out.println("연결 끊기");
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}
