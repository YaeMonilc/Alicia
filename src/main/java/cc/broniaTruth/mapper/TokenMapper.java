package cc.broniaTruth.mapper;

import cc.broniaTruth.Alicia;
import cc.broniaTruth.entity.Token;
import cc.broniaTruth.entity.User;
import cc.broniaTruth.manager.DatabaseManager;
import cc.broniaTruth.utils.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TokenMapper {
    public static ResultSet getTokens(){
        try {
            return DatabaseManager.getStatement().executeQuery("SELECT * FROM token");
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }
    public static ResultSet getTokensById(int id){
        try {
            PreparedStatement preparedStatement = Database.getConn()
                    .prepareStatement("SELECT * FROM token WHERE id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }
    public static ResultSet getTokensByUserId(int id){
        try {
            PreparedStatement preparedStatement = Database.getConn()
                    .prepareStatement("SELECT * FROM token WHERE user_id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }

    public static int addTokens(Token... Tokens){
        try {
            for (Token token : Tokens) {
                PreparedStatement preparedStatement = DatabaseManager.getConnection()
                        .prepareStatement("INSERT INTO token VALUES(null, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, token.getToken());
                preparedStatement.setString(2, token.getIp());
                preparedStatement.setString(3, token.getCreate_time());
                preparedStatement.setString(4, token.getExpire_time());
                preparedStatement.setInt(5, token.getUser_id());
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return 0;
    }

    public static List<Token> toEntityList(ResultSet resultSet){
        List<Token> tokenList = new ArrayList<>();
        try {
            while (resultSet.next()){
                tokenList.add(toEntity(resultSet));
            }
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return tokenList;
    }
    public static Token toEntity(ResultSet resultSet){
        try {
            Token token = new Token();
            token.setId(resultSet.getInt("id"));
            token.setToken(resultSet.getString("token"));
            token.setIp(resultSet.getString("ip"));
            token.setCreate_time(resultSet.getString("create_time"));
            token.setExpire_time(resultSet.getString("expire_time"));
            token.setUser_id(resultSet.getInt("user_id"));
            return token;
        } catch (SQLException e) {
            Alicia.getLogger().error(e.toString());
        }
        return null;
    }
}
