package cc.broniaTruth.service.http;

import io.javalin.http.Context;

import java.sql.SQLException;

public interface HttpService {
    void handle(Context context) throws SQLException;
}
