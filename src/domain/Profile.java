package domain;

import javax.swing.*;
import java.util.Calendar;
import java.util.HashMap;

public class Profile extends JFrame{
    private String name;
    private Calendar dateOfBirth;

    /**
     * Program program
     * Integer watchedPercentage
     */
    private HashMap<Program, Integer> watchedPrograms;
    private JPanel Labels;
    private JLabel usernameLbl;
    private JLabel firstNameLbl;
    private JPanel Textfields;
    private JTextField userNameTxt;
    private JTextField firstNameTxt;
    private JButton saveButton;
    private JPanel panel1;

    public Profile(String name, Calendar dateOfBirth, HashMap<Program, Integer> watchedPrograms) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.watchedPrograms = watchedPrograms;
    }

    public void buildForm(){
        add(panel1);

        setTitle("Account");
        setSize(600,600);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
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
