package actionListeners;

import datalayer.DAOAccount;
import datalayer.DAOConnection;
import datalayer.DAOProfile;
import domain.Account;
import domain.Profile;
import domain.Program;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class SaveProfileBtnListener implements ActionListener {
    private boolean editing = false;
    private JTextField name;
    private String Date;
    private JTextField AccountName;

    public SaveProfileBtnListener(boolean editing, JTextField name, JTextField date, JTextField accountsTable) {
        this.editing = editing;
        this.name = name;
        Date = date.getText();
        AccountName = accountsTable;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (!editing) {
            DAOProfile.getInstance().newProfile(Date, AccountName.getText(), name.getText());
        } else {

        }
    }
}
