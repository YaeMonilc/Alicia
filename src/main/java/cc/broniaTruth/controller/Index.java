package cc.broniaTruth.controller;

import cc.broniaTruth.Alicia;
import cc.broniaTruth.entity.User;
import cc.broniaTruth.entity.UserInfo;
import cc.broniaTruth.mapper.UserInfoMapper;
import cc.broniaTruth.service.http.Http;
import cc.broniaTruth.service.http.HttpService;
import cc.broniaTruth.mapper.UserMapper;
import io.javalin.http.Context;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Http(path = "/", mode = "GET")
public class Index implements HttpService {
    @Override
    public void handle(Context context) throws SQLException {
        List<User> userList = UserMapper.toEntityList(Objects.requireNonNull(UserMapper.getUsersByName("YaeMonilc")));
        Alicia.getLogger().error(userList.toString());
        if (userList.size() >= 1){
            List<UserInfo> userInfoList = UserInfoMapper.toEntityList(UserInfoMapper.getUserInfosById(userList.get(0).getInfo_id()));
            Alicia.getLogger().error(userInfoList.toString());
        }
        context.status(403);
    }
}
