package datalayer;

import domain.Account;
import domain.Profile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * this class is used to execute sql queries for accounts.
 */
public class DAOAccount {

    /**
     * singleton pattern.
     */
    private static DAOAccount instance;


    /**
     * Deletes an account from the database with the given name
     */
    public void delete(String name) {
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "Delete FROM dbo.Account WHERE Name = '" + name + "'";
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

    /**
     * Creates a new account
     */
    public void newAccount(String name, String street, String houseNumber, String houseNumberAddition, String Residence) {
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "INSERT INTO Account(Name, Street, HouseNumber, HouseNumberAddition, Residence) VALUES ('" + name + "','" + street + "','" + houseNumber + "','" + houseNumberAddition + "','" + Residence + "')";
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

    /**
     * Saves changes to old account
     */
    public void saveAccount(String name, String street, String houseNumber, String houseNumberAddition, String Residence) {
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "UPDATE Account SET Street = '" + street + "', HouseNumber = '" + houseNumber + "', HouseNumberAddition = '" + houseNumberAddition + "', Residence = '" + Residence + "' WHERE Account.Name = '" + name + "'";
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

    /**
     * Gets an account from the database with the given name
     *
     * @param name
     * @return account
     */
    public Account read(String name) {
        Account account = null;
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.Account WHERE Name = '" + name + "'";
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                account = new Account(
                        rs.getString("Name"),
                        rs.getString("Street"),
                        rs.getString("HouseNumber"),
                        rs.getString("HouseNumberAddition"),
                        rs.getString("Residence"),
                        null);

                ArrayList<Profile> profiles = DAOProfile.getInstance().readAllProfilesForAccount(account);
                account.setProfiles(profiles);
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

        return account;
    }

    /**
     * Gets all Accounts from the database and returns them.
     *
     * @return Set with all accounts.
     */
    public Set<Account> readAll() {
        Set<Account> accounts = new HashSet<>();
        Connection con = DAOConnection.getInstance().connect();

        try {
            Statement st = con.createStatement();
            String SQL = "SELECT * FROM dbo.Account";
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                Account ac = new Account(
                        rs.getString("Name"),
                        rs.getString("Street"),
                        rs.getString("HouseNumber"),
                        rs.getString("HouseNumberAddition"),
                        rs.getString("Residence"),
                        null);

                ArrayList<Profile> profiles = DAOProfile.getInstance().readAllProfilesForAccount(ac);
                ac.setProfiles(profiles);

                accounts.add(ac);
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

        return accounts;
    }

    public static DAOAccount getInstance() {
        if (instance == null) {
            instance = new DAOAccount();
        }
        return instance;
    }
}
