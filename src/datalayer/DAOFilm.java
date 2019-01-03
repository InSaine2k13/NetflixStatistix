package datalayer;

import domain.Film;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

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
            String SQL = "SELECT * FROM dbo.Film f INNER JOIN dbo.Program p ON f.ID = p.ID WHERE f.ProgramID IN (SELECT ID FROM Program p WHERE p.Title = '" + title + "')";
            ResultSet rs = st.executeQuery(SQL);

            while(rs.next()) {
                film = new Film(
                        rs.getString("Title"),
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

    public Set<Film> readAccount(String Account) {
        Set<Film> film = new HashSet<Film>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT Title, Duration, Genre, Language, AgeIndication FROM dbo.Film f INNER JOIN Program p ON f.ProgramID = p.ID INNER JOIN WatchedPrograms w ON p.ID = w.ProgramID INNER JOIN Profile pr ON w.ProfileID = pr.ID INNER JOIN Account a ON pr.AccountID=a.ID WHERE a.[Name] ='" + Account + "'";
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                film.add( new Film(
                        rs.getString("Title"),
                        rs.getInt("Duration"),
                        rs.getString("Genre"),
                        rs.getString("Language"),
                        rs.getInt("AgeIndication")
                ));
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


    // Reads the most watched child film from the database
    public Film readLongestDurationChildFilm() {
        Film film = null;
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT Title,ProgramType,p.ID,Duration,f.AgeIndication, F.Genre, F.Language FROM Program p INNER JOIN Film f ON p.id = f.ProgramID WHERE ProgramType = 'Film' AND Duration = (SELECT MAX(Duration) From Program) AND f.AgeIndication < 16";
            ResultSet rs = st.executeQuery(SQL);

            while(rs.next()) {
                film = new Film(
                        rs.getString("Title"),
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

    public Set<Film> readAll() {
        HashSet<Film> Films = new HashSet<>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
<<<<<<< HEAD
<<<<<<< HEAD
            String SQL = "SELECT * FROM dbo.Film INNER JOIN dbo.Program ON Film.ProgramID= Program.ID";
=======
            String SQL = "SELECT * FROM dbo.Film INNER JOIN dbo.Program ON Film.ProgramId = Program.Id";
>>>>>>> 38ec72daaf8acad56dce0f3dcc93a20fe848884c
=======
            String SQL = "SELECT * FROM dbo.Film INNER JOIN dbo.Program ON Film.ProgramId = Program.Id";
>>>>>>> 38ec72daaf8acad56dce0f3dcc93a20fe848884c
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {

                Film s = new Film(
                        rs.getString("Title"),
                        rs.getInt("Duration"),
                        rs.getString("Genre"),
                        rs.getString("Language"),
                        rs.getInt("AgeIndication")
                );



                Films.add(s);
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

        return Films;
    }

    public int GetAmountWatchedByFilm(int filmId){
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT COUNT(*) As total FROM WatchedPrograms WHERE WatchedPercentage = '100' AND ProgramID = '" + filmId + "'";
            ResultSet rs = st.executeQuery(SQL);
            return rs.getInt("total");
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
        return 0;
    }
}
