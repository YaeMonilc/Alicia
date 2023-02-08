package cc.broniaTruth.utils;

import cc.broniaTruth.Alicia;
import cc.broniaTruth.config.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection conn;

    private static final String url = Config.getProperties().getProperty("database_url");
    private static final String user = Config.getProperties().getProperty("database_user");
    private static final String password = Config.getProperties().getProperty("database_password");

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
