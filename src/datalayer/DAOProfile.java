package datalayer;

import domain.Account;
import domain.Profile;
import domain.Program;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * this class is used to execute sql queries for profiles.
 */
public class DAOProfile {

    private static DAOProfile instance;

    /**
     * reads all profiles from the database for the given account.
     * @param account
     * @return list with the profiles of the account
     */
    public ArrayList<Profile> readAllProfilesForAccount(Account account) {
        ArrayList<Profile> profiles = new ArrayList<>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.Profile WHERE AccountID = (SELECT ID FROM dbo.Account WHERE Name = '"+ account.getName() +"')";
            ResultSet rs = st.executeQuery(SQL);

            while(rs.next()) {
                java.sql.Date sqlDate = rs.getDate("DateOfBirth");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(new Date(sqlDate.getTime()));

                Profile p = new Profile(
                        rs.getString("Name"),
                        calendar,
                        null
                );

                HashMap<Program, Integer> watchedPrograms = readWatchedPrograms(rs.getInt("ID"));
                p.setWatchedPrograms(watchedPrograms);

                profiles.add(p);
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

        return profiles;
    }

    /**
     * reads all id's of the profiles of the given account
     * @param account
     * @return set with all profile id's
     */
    public Set<Integer> readAllProfileIDsForAccount(Account account) {
        Set<Integer> profileIDs = new HashSet<>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT ID FROM dbo.Profile WHERE AccountID = (SELECT ID FROM dbo.Account WHERE Name = '"+ account.getName() +"')";
            ResultSet rs = st.executeQuery(SQL);

            while(rs.next()) {
                profileIDs.add(rs.getInt("ID"));
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

        return profileIDs;
    }

    /**
     * reads the watched programs of the given profile
     * @param profileID
     * @return map with the program and percentage watched
     */
    public HashMap<Program, Integer> readWatchedPrograms(int profileID) {
        HashMap<Program, Integer> watchedPrograms = new HashMap<Program, Integer>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.WatchedPrograms WHERE ProfileID = " + profileID;
            ResultSet rs = st.executeQuery(SQL);

            while(rs.next()) {

                Program p = DAOProgram.getInstance().read(rs.getString("ProgramTitle"));
                int watchedPercentage = rs.getInt("WatchedPercentage");
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

        return watchedPrograms;
    }

    public static DAOProfile getInstance() {
        if(instance == null) {
            instance = new DAOProfile();
        }
        return instance;
    }

    public Set<Profile> readAll() {
        Set<Profile> profiles = new HashSet<>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.Profile";
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                Profile pf = new Profile(
                        rs.getString("Name"),
                        null,
                        null
                );
                profiles.add(pf);
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
        return profiles;
    }
}
