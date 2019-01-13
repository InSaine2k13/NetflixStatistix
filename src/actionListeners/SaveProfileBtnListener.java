package actionListeners;

import datalayer.DAOProfile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveProfileBtnListener implements ActionListener {
    private String saccountname;
    private String sname;
    private boolean editing = false;
    private JTextField name;
    private JTextField Date;
    private JTextField AccountName;

    public SaveProfileBtnListener(boolean editing, JTextField name, JTextField date, JTextField accountsTable) {
        this.editing = editing;
        this.name = name;
        AccountName = accountsTable;
        Date=date;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //make it possible to save the profile in the database
        String date = Date.getText();
        if (editing) {
            DAOProfile.getInstance().saveProfile(name.getText(), date,  AccountName.getText());
        } else {
            DAOProfile.getInstance().newProfile(date, AccountName.getText(), name.getText());
        }
    }
}
