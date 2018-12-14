package datalayer;

import domain.Film;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * this class is used to execute sql queries for films.
 */
public class DAOFilm {

    private static DAOFilm instance;

    /**
     * Gets a film from the database with the given title
     * @param title
     * @return film
     */
    public Film read(String title) {
        Film film = null;
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.Film f INNER JOIN dbo.Program p ON f.ProgramTitle = p.Title WHERE f.ProgramTitle = '"+ title.replace("'", "char(39)") + "'";
            ResultSet rs = st.executeQuery(SQL);

            while(rs.next()) {
                film = new Film(
                        rs.getString("ProgramTitle"),
                        rs.getInt("Duration"),
                        rs.getString("Genre"),
                        rs.getString("Language"),
                        rs.getInt("AgeIndication")
                );
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return film;
    }

    public static DAOFilm getInstance() {
        if(instance == null) {
            instance =  new DAOFilm();
        }
        return instance;
    }
}
