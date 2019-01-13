package domain;

import actionListeners.SaveProfileBtnListener;
import datalayer.DAOProfile;

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
    private int Account;
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
    private JLabel AccountNameLbl;

    public Profile(String name, Date dateOfBirth, HashMap<Program, Integer> watchedPrograms , int AccountName, MainForm m) {
        this.editing=true;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.watchedPrograms = watchedPrograms;
        this.Account=AccountName;
        userNameTxt.setEditable(false);
        AccountName1.setEditable(false);
        buildForm(m);
    }

    public Profile(JTable accountsTable,MainForm m) {

        this.editing = false;
        userNameTxt.setEditable(true);
        this.AcountID=accountsTable.getValueAt(accountsTable.getSelectedRow(),1).toString();
        buildForm(m);
    }

//code witch opens a new window to edit or create the profile
    public void buildForm(MainForm m){
        add(panel1);

        setTitle("Profile");
        setSize(600,600);
        userNameTxt.setText(name);
        firstNameTxt.setText(date);
        if (editing){
            AccountName1.setText(""+Account);
        }else if (!editing) {
            AccountName1.setText(AcountID);
        }

        saveButton.addActionListener(new SaveProfileBtnListener(editing, userNameTxt,firstNameTxt,AccountName1, m));
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
// adds a watch programs list to this object (new profile())
    public void setWatchedPrograms(HashMap<Program, Integer> watchedPrograms) {
        this.watchedPrograms = watchedPrograms;
    }
//get te account of this object
    public int getAccount() {
        return this.Account;
    }
    /**
     * adds the program and the time watched to the watchedPrograms map
     * @param program
     * @param timeWatched
     * not used in the end
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
