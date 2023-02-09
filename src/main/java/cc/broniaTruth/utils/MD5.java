package cc.broniaTruth.utils;

import cc.broniaTruth.Alicia;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {
            Alicia.getLogger().error(e.toString());
            System.exit(100);
        }
    }

    public static byte[] encrypt(byte[] bytes){
        md.update(bytes);
        return md.digest();
    }

    public static byte[] encrypt(String s){
        return encrypt(s.getBytes(StandardCharsets.UTF_8));
    }

    public static String encryptStr(byte[] bytes){
        return new BigInteger(1, encrypt(bytes)).toString(16);
    }

    public static String encryptStr(String s){
        return encryptStr(s.getBytes(StandardCharsets.UTF_8));
    }

}
