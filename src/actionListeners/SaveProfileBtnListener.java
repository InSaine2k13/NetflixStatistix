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
    private String Date;
    private JTextField AccountName;
    private String ID;

    public SaveProfileBtnListener(boolean editing, JTextField name, JTextField date, JTextField accountsTable) {
        this.editing = editing;
        this.name = name;
        Date = date.getText();
        AccountName = accountsTable;

    }

    public SaveProfileBtnListener(boolean b, String name, String change, String AcID) {
        this.editing=b;
        this.sname= name;
        this.saccountname=change;
        this.ID=AcID;
        DAOProfile.getInstance().saveProfile(sname, saccountname, ID);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (editing) {

        } else {
            DAOProfile.getInstance().newProfile(Date, AccountName.getText(), name.getText());
        }
    }
}
