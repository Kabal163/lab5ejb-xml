package ru.wow.util;


import ru.wow.cdiAnnotations.ServerUri;

import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    @Produces @ServerUri
    private String getServerUri(){
        String serverUri = null;
        Properties prop = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try(InputStream is = classLoader.getResourceAsStream("dataConfig.properties")){
            prop.load(is);
            serverUri = prop.getProperty("serverUri");
        } catch (IOException e) {
            System.out.println("Exception in reading property: " + e);
        }
        return serverUri;
    }
}
