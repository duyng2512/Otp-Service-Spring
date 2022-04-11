package config;

import utils.log.AppLog;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfig {
    private static final String FILE_CONFIG = "config.properties";
    private static final Properties properties = new Properties();

    static {

        try(InputStream inputStream = LoadConfig.class.getClassLoader().getResourceAsStream(FILE_CONFIG)) {
            properties.load(inputStream);
        } catch (IOException exception) {
            AppLog.error().error("Unable to load " + FILE_CONFIG, exception);
        } catch (NullPointerException exception){
            AppLog.error().error("File " + FILE_CONFIG + " not found", exception);
        }
    }

    public static String getProperties(String property){
        return properties.getProperty(property);
    }
}
