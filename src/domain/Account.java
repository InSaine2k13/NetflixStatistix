package domain;

import actionListeners.NewAccountBtnListener;
import actionListeners.SaveAccountBtnListener;

import javax.swing.*;
import java.util.ArrayList;

public class Account extends JFrame{
    private boolean editing;
    private String name;
    private String street;
    private String houseNumber;
    private String houseNumberAddition;
    private String residence;
    private ArrayList<Profile> profiles;

    private JPanel panel1;
    private JPanel Buttons;
    private JPanel Labels;
    private JPanel Textfields;
    private JLabel usernameLbl;
    private JLabel houseNumberAdditionLbl;
    private JTextField usernameTxt;
    private JTextField houseNumberTxt;
    private JButton saveButton;
    private JTextField houseNumberAdditionTxt;
    private JTextField streetTxt;
    private JTextField cityTxt;

    /**
     * Default constructor used when called by New Account button.
     */
    public Account()
    {
        this.editing = false;
        usernameTxt.setEditable(true);
        buildForm();
    }

    /**
     * Custom constructor used when called by Edit button.
     */
    public Account(String name, String street, String houseNumber, String houseNumberAddition, String residence, ArrayList<Profile> profiles) {
        this.editing = true;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.houseNumberAddition = houseNumberAddition;
        this.residence = residence;
        this.profiles = profiles;

        usernameTxt.setEditable(false);

        buildForm();
    }

    //method responsible for the pop-up window for editing and adding Accounts
    public void buildForm(){
        add(panel1);

        setTitle("Account");
        setSize(600,600);

        usernameTxt.setText(name);
        houseNumberTxt.setText(houseNumber);
        houseNumberAdditionTxt.setText(houseNumberAddition);
        streetTxt.setText(street);
        cityTxt.setText(residence);

        saveButton.addActionListener(new SaveAccountBtnListener(editing, usernameTxt, streetTxt, houseNumberTxt, houseNumberAdditionTxt,cityTxt));
    }

    //Method to get the Name of the account from another class
    public String getName() {
        return name;
    }

    //Method to Set the account name if it is not already set when the New Account is called if needed
    public void setName(String name) {
        this.name = name;
    }

    //Method to get the Street from the account from another class
    public String getStreet() {
        return street;
    }

    //Method to Set the street if it is not already set when the New Account is called when needed
    public void setStreet(String street) {
        this.street = street;
    }

    //Method to get the Housenumber of the account from another class
    public String getHouseNumber() {
        return houseNumber;
    }

    //Method to Set the Housenumber if it is not already set when the New Account is called when needed
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    //Get the HouseNumberAddition from another class
    public String getHouseNumberAddition() {
        return houseNumberAddition;
    }

    // change the HouseNumberAddition in the class
    public void setHouseNumberAddition(String houseNumberAddition) {
        this.houseNumberAddition = houseNumberAddition;
    }

    //Get the Residence of this account.
    public String getResidence() {
        return residence;
    }

    //Set the Residence of this account.
    public void setResidence(String residence) {
        this.residence = residence;
    }

    //Get all the profiles from this account
    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    //Add profiles to this class
    public void setProfiles(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", houseNumberAddition='" + houseNumberAddition + '\'' +
                ", residence='" + residence + '\'' +
                ", profiles=" + profiles +
                '}';
    }
}
