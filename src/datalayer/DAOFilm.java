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
                        rs.getInt("ID"),
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
    /**
     * Gets a watched film from the database with the given Account
     * @param Account
     * @return film
     */
    public Set<Film> readAccount(String Account) {
        Set<Film> film = new HashSet<Film>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT f.ID AS FilmID, Title, Duration, Genre, Language, AgeIndication , COUNT(*)FROM dbo.Film f INNER JOIN Program p ON f.ProgramID = p.ID INNER JOIN WatchedPrograms w ON p.ID = w.ProgramID INNER JOIN Profile pr ON w.ProfileID = pr.ID INNER JOIN Account a ON pr.AccountID=a.ID WHERE a.[Name] ='" + Account + "' GROUP BY  f.ID , Title, Duration, Genre, Language, AgeIndication HAVING Count(*) > 1";
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                film.add( new Film(
                        rs.getInt("FilmID"),
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
            String SQL = "SELECT Title,ProgramType,p.ID,Duration,f.AgeIndication, F.Genre, F.[Language] FROM Program p INNER JOIN Film f ON p.id = f.ProgramID WHERE ProgramType = 'Film' AND Duration = (SELECT MAX(Duration) From Program WHERE ID IN (SELECT ProgramID FROM Film WHERE AgeIndication < '16') )";
            ResultSet rs = st.executeQuery(SQL);

            while(rs.next()) {
                film = new Film(
                        rs.getInt("ID"),
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
//reads all films in the film database
    public Set<Film> readAll() {
        HashSet<Film> Films = new HashSet<>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.Film INNER JOIN dbo.Program ON Film.ProgramID= Program.ID";
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {

                Film s = new Film(
                        rs.getInt("ID"),
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

    //all films that have been fully watched
    public int GetAmountWatchedFullyByFilm(int filmId){
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT COUNT(*) as total FROM WatchedPrograms INNER JOIN Program ON WatchedPrograms.ProgramID = Program.ID INNER JOIN Film ON Program.ID = Film.ProgramID WHERE WatchedPercentage = '100' AND Film.Id = '"+ filmId +"'";
            ResultSet rs = st.executeQuery(SQL);
            if(rs.next()){
                return rs.getInt("total");
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
        return 0;
    }

    //get how many times this film is watched in total
    public int GetAmountWatchedByFilm(int filmId){
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT COUNT(*) as total FROM WatchedPrograms INNER JOIN Program ON WatchedPrograms.ProgramID = Program.ID INNER JOIN Film ON Program.ID = Film.ProgramID WHERE WatchedPercentage > '0' AND Film.Id = '"+ filmId +"'";
            ResultSet rs = st.executeQuery(SQL);
            if(rs.next()){
                return rs.getInt("total");
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
        return 0;
    }
}
