package cc.broniaTruth.config;

import cc.broniaTruth.Alicia;
import jakarta.annotation.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try {
            InputStream in = Resources.class.getResourceAsStream("/config.properties");
            if (!Objects.isNull(in)){
                InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
                properties.load(inputStreamReader);
            }
        } catch (IOException e) {
            Alicia.getLogger().error(e.toString());
            System.exit(100);
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
