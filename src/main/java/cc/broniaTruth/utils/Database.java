package cc.broniaTruth.utils;

import cc.broniaTruth.Alicia;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection conn;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/db?useSSL=false";
    private static final String user = "root";
    private static final String password = "Sen060908.=";

    public static void connect() {
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
            System.exit(100);
        }
    }

    public static Connection getConn() {
        return conn;
    }
}
