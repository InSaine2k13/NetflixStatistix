package datalayer;

import domain.Account;
import domain.Episode;
import domain.Serie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * this class is used to execute sql queries for series.
 */
public class DAOSerie {

    /**
     * Singleton pattern.
     */
    private static DAOSerie instance;

    /**
     * Gets a serie with the given title from the database.
     * @param serieTitle
     * @return Serie
     */
    public Serie read(String serieTitle) {
        Connection con = DAOConnection.getInstance().connect();
        Serie serie = null;

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.Serie WHERE Title = '" + serieTitle.replace("'", "char(39)") + "'";
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {

                Serie s = new Serie(
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getString("Language"),
                        rs.getInt("AgeIndication")
                );

                s.setEpisodes(DAOEpisode.getInstance().readAllEpisodesForSerie(s));

                serie = s;

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

        return serie;
    }

    /**
     * Gets all series from the database
     * @return all series
     */
    public Set<Serie> readAll() {
        HashSet<Serie> series = new HashSet<>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.Serie";
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {

                Serie s = new Serie(
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getString("Language"),
                        rs.getInt("AgeIndication")
                );

                s.setEpisodes(DAOEpisode.getInstance().readAllEpisodesForSerie(s));

                series.add(s);
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

        return series;
    }

    /**
     * returns the average watch time of each episode in the given serie.
     * @param serie
     * @return map with episodes and the average watch time
     */
    public Map<Episode, Integer> readAverageWatchTime(Serie serie) {
        Map<Episode, Integer> episodes = new HashMap<>();
        Connection con = DAOConnection.getInstance().connect();

        try {

            for(Episode e : serie.getEpisodes()) {
                Statement st = con.createStatement();
                String title =  e.getTitle();
                String SQL = "SELECT WatchedPercentage FROM dbo.WatchedPrograms w INNER JOIN Program p ON w.ProgramID = p.ID WHERE Title = '" + title+ "'";
                ResultSet rs = st.executeQuery(SQL);

                int timesWatched = 0;
                int totalWatchTime = 0;

                while (rs.next()) {
                    timesWatched++;
                    totalWatchTime += rs.getInt("WatchedPercentage");
                }

                if(timesWatched == 0 || totalWatchTime == 0) {
                    episodes.put(e, 0);
                } else {
                    int averageWatchPercentage = (totalWatchTime / timesWatched);
                    episodes.put(e, averageWatchPercentage);
                }

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

        return episodes;
    }

    /**
     * returns the average watch time of each episode for the given account in the given serie.
     * @param account
     * @param serie
     * @return map with episodes and the average watch time
     */
    public Map<Episode, Integer> readAverageWatchTimeForAccount(Account account, Serie serie) {
        Map<Episode, Integer> episodes = new HashMap<>();
        Connection con = DAOConnection.getInstance().connect();

        try {

            for(Episode e : serie.getEpisodes()) {
                Statement st = con.createStatement();
                String title =  e.getTitle();
                int timesWatched = 0;
                int totalWatchTime = 0;

                for(int profileID : DAOProfile.getInstance().readAllProfileIDsForAccount(account)) {
                    String SQL = "SELECT WatchedPercentage FROM dbo.WatchedPrograms w INNER JOIN Program p ON W.ProgramID = P.ID WHERE Title = '" + title + "' AND ProfileID = " + profileID;
                    ResultSet rs = st.executeQuery(SQL);

                    while (rs.next()) {
                        timesWatched++;
                        totalWatchTime += rs.getInt("WatchedPercentage");
                    }
                }

                if(timesWatched == 0 || totalWatchTime == 0) {
                    episodes.put(e, 0);
                } else {
                    int averageWatchPercentage = (totalWatchTime / timesWatched);
                    episodes.put(e, averageWatchPercentage);
                }

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

        return episodes;
    }

    public static DAOSerie getInstance() {
        if (instance == null) {
            instance = new DAOSerie();
        }
        return instance;
    }


}
