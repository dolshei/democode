package com.example.democode.simple.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTests {
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("DB Connection Test")
    @Test
    public void testConnection() {
        // try(Statement) : 소괄호 안에 close를 필요로 하는 인스턴스를 작성하게 되면
        // try문이 끝났을 때 자동으로 close()를 실행해준다.

        try(Connection con =
                    DriverManager.getConnection(
                            "jdbc:mariadb://localhost:3306/example_db?serverTimezone=Asia/Seoul",
                            "root",
                            "6h5g4f#D@S!A")){
            System.out.println("Connection : " + con);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
