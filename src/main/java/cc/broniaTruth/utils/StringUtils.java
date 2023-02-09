package cc.broniaTruth.utils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;

public class StringUtils {
    private static final char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public static boolean isNullOrEmpty(String s){
        return Objects.isNull(s) || s.isEmpty();
    }

    public static String random(int length, boolean noRepeat){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(chars.length);
            stringBuilder.append(chars[num]);
        }
        if (noRepeat){
            stringBuilder.append(System.currentTimeMillis());
        }
        return Base64.encoder.encodeToString(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
    }
}
