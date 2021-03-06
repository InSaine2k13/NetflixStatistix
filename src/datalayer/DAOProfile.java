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
     *
     * @param account
     * @return list with the profiles of the account
     */
    public ArrayList<Profile> readAllProfilesForAccount(Account account) {
        ArrayList<Profile> profiles = new ArrayList<>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.Profile WHERE AccountID = (SELECT ID FROM dbo.Account WHERE Name = '" + account.getName() + "')";
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                java.sql.Date sqlDate = rs.getDate("DateOfBirth");
                Date calendar = new Date();
                calendar.getTime();

                Profile p = new Profile(
                        rs.getString("Name"),
                        calendar,
                        null,
                        rs.getInt("AccountID"),
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
     *
     * @param account
     * @return set with all profile id's
     */
    public Set<Integer> readAllProfileIDsForAccount(Account account) {
        Set<Integer> profileIDs = new HashSet<>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT ID FROM dbo.Profile WHERE AccountID = (SELECT ID FROM dbo.Account WHERE Name = '" + account.getName() + "')";
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
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
     *
     * @param profileID
     * @return map with the program and percentage watched
     */
    public HashMap<Program, Integer> readWatchedPrograms(int profileID) {
        HashMap<Program, Integer> watchedPrograms = new HashMap<Program, Integer>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.WatchedPrograms w INNER JOIN Program p ON w.ProgramID=p.ID WHERE ProfileID = " + profileID;
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {

                Program p = DAOProgram.getInstance().read(rs.getString("Title"));
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
// makes the class accesible to the other classes
    public static DAOProfile getInstance() {
        if (instance == null) {
            instance = new DAOProfile();
        }
        return instance;
    }
    // get all the profiles in the database
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
                        null,
                        rs.getInt("AccountID"),
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

    // adds a new profile to the database
    public void newProfile(String DateOfBirth, String AcountID, String ProfileName) {
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "INSERT INTO Profile(DateOfBirth, AccountID, Name) VALUES ('" + DateOfBirth + "', (SELECT ID FROM Account WHERE [Name]='" + AcountID + "') ,'" + ProfileName + "')";
            st.execute(SQL);

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
    }
// edit a profile in the database
    public void saveProfile(String accountname, String Date, String id) {
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "UPDATE Profile SET Profile.[DateOfBirth] = CAST('" + Date + "' AS DATE) WHERE Profile.[Name] = '" + accountname + "' AND Profile.AccountID = '" + id + "'";
            st.execute(SQL);

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

}
//removes a profile from the database
    public void delete(String name, String AccountID) {
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "Delete FROM dbo.Profile WHERE Name = '" + name + "' AND AccountID= '" + AccountID + "'";
            st.execute(SQL);

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
    }

    //saves a episode of a serie in the watched database when watched by a profile
    public void newWatchedSerie(String Serie,String ID, String Episode, String Profile, String Language, String percentage) {
        Connection con = DAOConnection.getInstance().connect();
        try {
            Statement st = con.createStatement();
            String SQL = "INSERT INTO WatchedPrograms(ProfileID, ProgramID, WatchedPercentage) VALUES ((SELECT ID FROM Profile WHERE [Name] = '" + Profile + "' AND AccountID='"+ ID +"'), (SELECT p.ID FROM Program p INNER JOIN Episode e ON p.ID=e.ProgramID INNER JOIN Serie s ON e.SerieTitle=s.Title WHERE SerieTitle = '" + Serie + "' AND p.Title = '" + Episode + "' AND s.[Language]='"+ Language +"'), '"+ percentage +"' )";
            st.execute(SQL);

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
    }
    //saves a Film in the watched database when watched by a profile
    public void newWatchedFilm(String Film,String ID, String account, String Language, String percentage) {
        Connection con = DAOConnection.getInstance().connect();
                try {
            Statement st = con.createStatement();
            String SQL = "INSERT INTO WatchedPrograms(ProfileID, ProgramID, WatchedPercentage) VALUES ((SELECT ID FROM Profile WHERE [Name] = '" + account + "'AND AccountID='"+ ID +"'), (SELECT TOP(1) p.ID FROM Program p INNER JOIN Film f ON p.ID=f.ProgramID WHERE Title = '" + Film + "' AND f.Language='"+ Language +"'), '"+ percentage +"' )";
            st.execute(SQL);

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
    }
}