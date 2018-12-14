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
     * Default constructor used when called by New button.
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberAddition() {
        return houseNumberAddition;
    }

    public void setHouseNumberAddition(String houseNumberAddition) {
        this.houseNumberAddition = houseNumberAddition;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

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
