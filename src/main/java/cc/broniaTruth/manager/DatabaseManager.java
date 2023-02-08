package cc.broniaTruth.manager;

import cc.broniaTruth.Alicia;
import cc.broniaTruth.utils.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static Statement statement;
    private static Connection connection;

    static {
        try {
            connection = Database.getConn();
            statement = connection.createStatement();
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
            System.exit(100);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }
}
