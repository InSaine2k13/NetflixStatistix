package datalayer;

import domain.Program;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * this class is used to execute sql queries for programs.
 */
public class DAOProgram {

    /**
     * Singleton pattern
     */
    private static DAOProgram instance;

    /**
     * Gets a program from the database, uses ProgramType to determine if it is a film or an episode.
     * @param title
     * @return program
     */
    public Program read(String title) {
        Program program = null;
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT ProgramType FROM dbo.Program WHERE Title = title";
            ResultSet rs = st.executeQuery(SQL);

            while(rs.next()) {
                String type = rs.getString("ProgramType");
                if(type.equals("Film")) {
                    program = DAOFilm.getInstance().read(title);
                } else {
                    program = DAOEpisode.getInstance().read(title);
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

        return program;
    }

    public static DAOProgram getInstance() {
        if(instance == null) {
            instance = new DAOProgram();
        }

        return instance;
    }
}
