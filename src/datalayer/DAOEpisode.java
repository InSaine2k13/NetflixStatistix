package datalayer;

import domain.Episode;
import domain.Serie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * this class is used to execute sql queries for episodes.
 */
public class DAOEpisode {
    private static DAOEpisode instance;

    /**
     * Gets a episode from the database with the given title
     * @param title
     * @return episode
     */
    public Episode read(String title) {
        Episode episode = null;
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.Episode e INNER JOIN dbo.Program p ON e.ProgramTitle = p.Title WHERE e.ProgramTitle = '"+ title.replace("'", "char(39)") + "'";
            ResultSet rs = st.executeQuery(SQL);

            while(rs.next()) {
                episode = new Episode(
                        rs.getString("Title"),
                        rs.getInt("Duration"),
                        rs.getInt("EpisodeNr")
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

        return episode;
    }

    /**
     * Gets all episodes for the given serie from the database
     * @param s
     * @return all episodes of serie
     */
    public ArrayList<Episode> readAllEpisodesForSerie(Serie s) {
        ArrayList<Episode> episodes = new ArrayList<>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String title = s.getTitle();
            String SQL1 = "SELECT * FROM dbo.Episode e INNER JOIN dbo.Program p on p.Title = e.ProgramTitle WHERE SerieTitle = '" + title.replace("'", "char(39)") + "'";
            ResultSet rs = st.executeQuery(SQL1);

            while (rs.next()) {
                episodes.add(new Episode(
                        rs.getString("Title"),
                        rs.getInt("Duration"),
                        rs.getInt("EpisodeNr")
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

        return episodes;
    }

    public static DAOEpisode getInstance() {
        if(instance == null) {
            instance = new DAOEpisode();
        }
        return instance;
    }
}
