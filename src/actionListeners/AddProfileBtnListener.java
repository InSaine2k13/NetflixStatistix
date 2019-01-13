package actionListeners;

import datalayer.DAOProfile;
import domain.MainForm;
import domain.Profile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddProfileBtnListener implements ActionListener {
    private JTable accountTable;
    private MainForm m;

    //adds a new profile to the classes so it can be setup for the database
    public AddProfileBtnListener(JTable accountsTable, MainForm m) {
        this.accountTable = accountsTable;
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (accountTable.getSelectedRow() > -1) {
            new Profile(accountTable, m).setVisible(true);
        }
    }
}
