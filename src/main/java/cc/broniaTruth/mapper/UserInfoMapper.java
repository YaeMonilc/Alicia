package cc.broniaTruth.mapper;

import cc.broniaTruth.Alicia;
import cc.broniaTruth.entity.User;
import cc.broniaTruth.entity.UserInfo;
import cc.broniaTruth.manager.DatabaseManager;
import cc.broniaTruth.utils.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfoMapper {
    public static ResultSet getUserInfos(){
        try {
            return DatabaseManager.getStatement().executeQuery("SELECT * FROM user_info");
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }
    public static ResultSet getUserInfosById(int id){
        try {
            PreparedStatement preparedStatement = Database.getConn()
                    .prepareStatement("SELECT * FROM user_info WHERE id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }

    public static int addUserInfos(UserInfo... userInfos){
        try {
            for (UserInfo userInfo : userInfos) {
                PreparedStatement preparedStatement = DatabaseManager.getConnection()
                        .prepareStatement("INSERT INTO user_info VALUES(null, ?, ?)");
                preparedStatement.setString(1, userInfo.getProfile());
                preparedStatement.setString(2, userInfo.getSign());
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return 0;
    }

    public static List<UserInfo> toEntityList(ResultSet resultSet){
        List<UserInfo> userInfoList = new ArrayList<>();
        try {
            while (resultSet.next()){
                userInfoList.add(toEntity(resultSet));
            }
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return userInfoList;
    }
    public static UserInfo toEntity(ResultSet resultSet){
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(resultSet.getInt("id"));
            userInfo.setProfile(resultSet.getString("profile"));
            userInfo.setSign(resultSet.getString("sign"));
            return userInfo;
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }
}
