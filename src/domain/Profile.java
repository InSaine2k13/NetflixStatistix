package domain;

import actionListeners.SaveProfileBtnListener;

import javax.swing.*;
import java.util.Date;
import java.util.HashMap;

public class Profile extends JFrame{



    /**
     * Program program
     * Integer watchedPercentage
     */
    private boolean editing;
    private String name;
    private Date dateOfBirth;
    private String AcountID;
    private String date;

    private HashMap<Program, Integer> watchedPrograms;
    private JPanel Labels;
    private JLabel usernameLbl;
    private JLabel firstNameLbl;
    private JPanel Textfields;
    private JTextField userNameTxt;
    private JTextField firstNameTxt;
    private JTextField AccountName1;
    private JButton saveButton;
    private JPanel panel1;
    private JTextField AccountName;
    private JTable accountsTable;

    public Profile(String name, Date dateOfBirth, HashMap<Program, Integer> watchedPrograms) {
        this.editing=true;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.watchedPrograms = watchedPrograms;
        buildForm();
    }

    public Profile(JTable accountsTable) {
        this.editing = false;
        userNameTxt.setEditable(true);
        this.accountsTable=accountsTable;
        buildForm();
    }

    public void buildForm(){
        add(panel1);

        setTitle("Account");
        setSize(600,600);
        userNameTxt.setText(name);
        firstNameTxt.setText(date);
        AccountName1.setText(AcountID);

        saveButton.addActionListener(new SaveProfileBtnListener(editing, userNameTxt,firstNameTxt,AccountName1));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public HashMap<Program, Integer> getWatchedPrograms() {
        return watchedPrograms;
    }

    public void setWatchedPrograms(HashMap<Program, Integer> watchedPrograms) {
        this.watchedPrograms = watchedPrograms;
    }

    /**
     * adds the program and the time watched to the watchedPrograms map
     * @param program
     * @param timeWatched
     */
    public void watch(Program program, int timeWatched) {
        watchedPrograms.put(program, timeWatched);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", watchedPrograms=" + watchedPrograms +
                '}';
    }
}
