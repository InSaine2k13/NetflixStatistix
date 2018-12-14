package util;

import javax.naming.ConfigurationException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is used to read from config.properties.
 */
public class ConfigReader {
    /**
     * Singleton pattern.
     */
    private static ConfigReader instance;

    /**
     * the properties defined in the config.properties file
     */
    private Properties properties;

    /**
     * Constructor method
     * Reads all properties from the config.properties file and stores them in the class variable properties.
     */
    public ConfigReader() {
        properties = new Properties();

        try {
            String propFileName = "config.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * returns an jdbc sql authentication url based on configured values in config.properties
     * @return the connectionURL
     */
    public String getConnectionURL() {
        StringBuilder connectionURL = new StringBuilder();
        try {
            //protocol
            connectionURL.append("jdbc:sqlserver://");

            //server location
            connectionURL.append(properties.getProperty("serverLocation"));

            //server port
            connectionURL.append(":" + properties.getProperty("port") + ";");

            //name of the database to use
            connectionURL.append("databaseName=" + properties.getProperty("databaseName") + ";");

            //authentication type
            String authType = properties.getProperty("authenticationType");

            if (authType.toLowerCase().equals("integrated")) {
                connectionURL.append("integratedSecurity=true");
            } else if (authType.toLowerCase().equals("sql")) {
                //username for sql server
                connectionURL.append("user=" + properties.getProperty("username") + ";");

                //password for sql server
                connectionURL.append("password=" + properties.getProperty("password"));
            } else {
                throw new ConfigurationException("authenticationType " + authType + " is not a valid authenticationType, use integrated or sql instead");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return connectionURL.toString();
    }

    public static ConfigReader getInstance() {
        if(instance == null) {
            instance = new ConfigReader();
        }

        return instance;
    }


}
