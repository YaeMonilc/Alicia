package cc.broniaTruth.entity;

import cc.broniaTruth.Alicia;

public class UserInfo {
    private int id;
    private String profile;
    private String sign;

    public UserInfo() {
    }

    public UserInfo(String profile, String sign) {
        this.profile = profile;
        this.sign = sign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return Alicia.getGson().toJson(this);
    }
}
