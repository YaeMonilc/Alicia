package cc.broniaTruth.manager;

import cc.broniaTruth.Alicia;
import cc.broniaTruth.config.Config;
import cc.broniaTruth.service.http.Http;
import cc.broniaTruth.service.http.HttpService;
import io.javalin.Javalin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class HttpServicesManager {
    private static final Javalin javalin = Javalin.create();
    private static final Map<Http, HttpService> httpServiceList = new HashMap<>();

    static {
        getAllHttpService();
        getJavalin().start(Config.getProperties().getProperty("http_ip"),
                Integer.parseInt(Config.getProperties().getProperty("http_port")));
    }

    public static void runAllServices(){
        httpServiceList.forEach((http, httpService) -> {
            switch (http.mode()) {
                case "GET" -> getJavalin().get(http.path(), httpService::handle);
                case "POST" -> getJavalin().post(http.path(), httpService::handle);
                case "PUT" -> getJavalin().put(http.path(), httpService::handle);
                case "PATCH" -> getJavalin().patch(http.path(), httpService::handle);
                case "DELETE" -> getJavalin().delete(http.path(), httpService::handle);
            }
        });
    }

    public static void stopAllServices(){
        getJavalin().stop();
    }

    private static void getAllHttpService(){
        Reflections reflections = new Reflections("cc.broniaTruth.controller");
        reflections.getSubTypesOf(HttpService.class).forEach(aClass -> {
            if (aClass.isAnnotationPresent(Http.class)){
                Http http = aClass.getAnnotation(Http.class);
                if (httpServiceList.keySet().stream()
                        .filter(httpInfo -> httpInfo.path().equals(http.path()))
                        .toList().size() > 1){
                    return;
                }
                try {
                    HttpService httpService = aClass.getDeclaredConstructor().newInstance();
                    getHttpServiceList().put(http, httpService);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    Alicia.getLogger().error(e.toString());
                }
            }
        });
    }

    public static Javalin getJavalin() {
        return javalin;
    }

    public static Map<Http, HttpService> getHttpServiceList() {
        return httpServiceList;
    }
}
