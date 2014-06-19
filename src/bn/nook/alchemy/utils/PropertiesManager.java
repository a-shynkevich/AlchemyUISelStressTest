package bn.nook.alchemy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Alecs on 18.06.2014.
 */
public class PropertiesManager {
    private static Properties properties;

    public static void init() {

        try {
            loadProperties();
        } catch (IOException e) {
            closeApp("Unable to load configuration file: " + Constant.Path.CONFIGURATION_FILE_PATH);
        }

    }

    public static String getProperty(String propertyKey) {
        Enumeration enuKeys = properties.keys();
        while (enuKeys.hasMoreElements()) {
            String key = (String) enuKeys.nextElement();

            if(!key.equalsIgnoreCase(propertyKey)) continue;

            String value = properties.getProperty(key);

            return value;
        }

        return null;
    }

    private static void loadProperties() throws IOException {
        File configProperties = new File(Constant.Path.CONFIGURATION_FILE_PATH);
        FileInputStream fileInput = new FileInputStream(configProperties);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        setProperties(properties);
    }

    public static void setProperties(Properties properties) {
        PropertiesManager.properties = properties;
    }

    public static void closeApp(String s) {
        System.exit(0);
    }
}
