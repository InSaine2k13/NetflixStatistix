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
    private String ID;

    public SaveProfileBtnListener(boolean editing, JTextField name, JTextField date, JTextField accountsTable) {
        this.editing = editing;
        this.name = name;
        AccountName = accountsTable;
        Date=date;

    }

    public SaveProfileBtnListener(boolean b, String name, String change, String AcID) {
        this.editing=b;
        this.sname= name;
        this.saccountname=change;
        this.ID=AcID;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String date = Date.getText();
        if (editing) {
            DAOProfile.getInstance().saveProfile(name.getText(), date,  AccountName.getText());
        } else {
            DAOProfile.getInstance().newProfile(date, AccountName.getText(), name.getText());
        }
    }
}
