package cc.broniaTruth;

import cc.broniaTruth.manager.HttpServicesManager;
import cc.broniaTruth.utils.Database;
import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import org.slf4j.LoggerFactory;

public class Alicia {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Alicia.class);
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        Database.connect();
        HttpServicesManager.runAllServices();
    }

    public static Logger getLogger() {
        return logger;
    }

    public static Gson getGson() {
        return gson;
    }
}