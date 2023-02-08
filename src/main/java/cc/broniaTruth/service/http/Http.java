package cc.broniaTruth.service.http;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Http {
    String path() default "/";
    String mode() default "GET";
}
