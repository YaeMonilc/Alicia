package cc.broniaTruth.mapper;

import cc.broniaTruth.Alicia;
import cc.broniaTruth.entity.User;
import cc.broniaTruth.manager.DatabaseManager;
import cc.broniaTruth.utils.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static ResultSet getUsers(){
        try {
            return DatabaseManager.getStatement().executeQuery("SELECT * FROM user");
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }
    public static ResultSet getUsersById(int id){
        try {
            PreparedStatement preparedStatement = Database.getConn()
                    .prepareStatement("SELECT * FROM user WHERE id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }
    public static ResultSet getUsersByName(String name){
        try {
            PreparedStatement preparedStatement = Database.getConn()
                    .prepareStatement("SELECT * FROM user WHERE name = ?");
            preparedStatement.setString(1, name);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }
    public static ResultSet getUsersByAccount(String account){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnection()
                    .prepareStatement("SELECT * FROM user WHERE account = ?");
            preparedStatement.setString(1, account);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }

    public static int addUsers(User... users){
        try {
            for (User user : users) {
                PreparedStatement preparedStatement = DatabaseManager.getConnection()
                        .prepareStatement("INSERT INTO user VALUES(null, ?, ?, ?, ?)");
                preparedStatement.setString(1, user.getAccount());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getName());
                preparedStatement.setInt(4, user.getInfo_id());
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return 0;
    }

    public static List<User> toEntityList(ResultSet resultSet){
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()){
                userList.add(toEntity(resultSet));
            }
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return userList;
    }
    public static User toEntity(ResultSet resultSet){
        try {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setAccount(resultSet.getString("account"));
            user.setPassword(resultSet.getString("password"));
            user.setInfo_id(resultSet.getInt("info_id"));
            return user;
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }
}
