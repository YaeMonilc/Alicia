package cc.broniaTruth.controller;

import cc.broniaTruth.entity.ResultValue;
import cc.broniaTruth.entity.Token;
import cc.broniaTruth.entity.User;
import cc.broniaTruth.mapper.TokenMapper;
import cc.broniaTruth.mapper.UserMapper;
import cc.broniaTruth.service.http.Http;
import cc.broniaTruth.service.http.HttpService;
import cc.broniaTruth.utils.DateTimeUtils;
import cc.broniaTruth.utils.MD5;
import cc.broniaTruth.utils.StringUtils;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Http(path = "/login", mode = "POST")
public class Login implements HttpService {
    @Override
    public void handle(Context context) throws SQLException {
        String account = context.formParam("account");
        String password = context.formParam("password");

        if (StringUtils.isNullOrEmpty(account) ||
                StringUtils.isNullOrEmpty(password)){
            context.status(HttpStatus.PRECONDITION_FAILED);
            return;
        }

        assert account != null;
        assert password != null;

        ResultSet userSet = UserMapper.getUsersByAccountAndPassword(account, MD5.encryptStr(password));
        if (Objects.isNull(userSet)){
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            return;
        }
        List<User> userList = UserMapper.toEntityList(userSet);
        if (userList.size() < 1){
            context.result(new ResultValue(ResultValue.Codes.ACCOUNT_NOT_EXISTS_OR_PASSWORD_ERROR).toString());
            return;
        }

        User user = userList.get(0);

        Calendar expire = Calendar.getInstance();
        expire.setTime(new Date());
        expire.add(Calendar.DAY_OF_WEEK, 7);

        Token token = new Token(
                StringUtils.random(20, true),
                context.ip(),
                DateTimeUtils.toMysql(new Date()),
                DateTimeUtils.toMysql(expire.getTime()),
                user.getId()
        );

        TokenMapper.addTokens(token);

        context.result(new ResultValue(ResultValue.Codes.SUCCESS, token.getToken()).toString());
    }
}
