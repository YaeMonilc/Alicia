package cc.broniaTruth.entity;

import cc.broniaTruth.Alicia;

public class Token {
    private int id;
    private String token;
    private String ip;
    private String create_time;
    private String expire_time;
    private int user_id;

    public Token() {
    }

    public Token(String token, String ip, String create_time, String expire_time, int user_id) {
        this.token = token;
        this.ip = ip;
        this.create_time = create_time;
        this.expire_time = expire_time;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    @Override
    public String toString() {
        return Alicia.getGson().toJson(this);
    }
}
