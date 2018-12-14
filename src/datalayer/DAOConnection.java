package datalayer;

import util.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to create a connection to the database.
 */
public class DAOConnection {

    /**
     * The connection url used to connect to the database
     */
    private String connectionUrl;

    /**
     * singleton pattern.
     */
    private static DAOConnection instance;

    /**
     * constructor
     * uses ConfigReader#getConnectionURL() to set the connectionUrl
     */
    private DAOConnection() {
        connectionUrl = ConfigReader.getInstance().getConnectionURL();
    }

    /**
     * Gets a connection to the database using the connectionUrl.
     * @return the connection to the database
     */
    public Connection connect() {
        Connection conn = null;
        try {
           conn = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static DAOConnection getInstance() {
        if(instance == null) {
            instance = new DAOConnection();
        }

        return instance;
    }
}
