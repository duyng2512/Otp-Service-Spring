package config;

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
            exception.printStackTrace();
        } catch (NullPointerException exception){
            System.err.println("File " + FILE_CONFIG + " not found");
        }
    }

    public static String getProperties(String property){
        return properties.getProperty(property);
    }
}
