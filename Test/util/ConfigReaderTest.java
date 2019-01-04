package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReaderTest {
    private Properties properties;


    @Test
    void getConnectionURL() {
        //Arange
        properties = new Properties();

        try {
            String propFileName = "config.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ACT
        String authType = properties.getProperty("authenticationType");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String iswindows = System.getProperty("os.name");
        boolean windows = iswindows.contains("Windows");

        //ASSERT
        //integrated
        if (authType.toLowerCase().equals("integrated")) {
            try {
                Assertions.assertTrue(iswindows.contains("Window"));
            } catch (AssertionFailedError a) {
                throw new AssertionFailedError(
                        String.format("You need to use Windows for Integrated to work"));
            }
            //sql
        } else if (authType.toLowerCase().equals("sql")) {
            try {
                Assertions.assertTrue(authType.equals("sql") && (!username.isEmpty() || !password.isEmpty()));
            } catch (AssertionFailedError a) {
                throw new AssertionFailedError(
                        String.format("Username or password are empty"));
            }
        }
    }
}


