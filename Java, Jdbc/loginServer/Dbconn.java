package com.koreait.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconn {
    private static Connection conn; // 주요하게 생각하는것을 컨넥션 객체를 사용할 수 있게
    //스테틱이 있으면 클래스만 불러도 된다 메소드 호출시
    public static Connection getConnection() throws SQLException, ClassNotFoundException {// 리턴타입은 Connection
        String url = "jdbc:mysql://127.0.0.1/aidev?useSSL=false";
        String uid = "root";
        String upw = "1234";

        Class.forName("com.mysql.cj.jdbc.Driver");
         conn = DriverManager.getConnection(url, uid, upw);
        return conn;
    }
}
