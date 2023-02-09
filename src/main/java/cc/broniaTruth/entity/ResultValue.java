package cc.broniaTruth.entity;

import cc.broniaTruth.Alicia;
import io.javalin.http.HttpStatus;

public class ResultValue {
    private int code = Codes.SUCCESS;
    private String value = "";

    public static class Codes {
        public static final int SUCCESS = 200;
        public static final int NOT_FOUND = 404;
        public static final int PRECONDITION_FAILED = 412;
        public static final int INTERNAL_SERVER_ERROR = 500;

        public static final int TEST = 2000;

        public static final int ACCOUNT_NOT_EXISTS_OR_PASSWORD_ERROR = 2100;
    }

    public ResultValue() {
    }

    public ResultValue(int code) {
        this.code = code;
    }

    public ResultValue(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return Alicia.getGson().toJson(this);
    }
}
