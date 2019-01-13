package actionListeners;

import datalayer.DAOAccount;
import domain.Account;
import domain.MainForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAccountBtnListener implements ActionListener {
    private MainForm mainForm;
    private JTable accountsTable;
// removes the selected account from the database
    public DeleteAccountBtnListener(MainForm mainForm,JTable accountsTable) {
        this.mainForm = mainForm;
        this.accountsTable = accountsTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (accountsTable.getSelectedRow() > -1) {
            DAOAccount.getInstance().delete((accountsTable.getValueAt(accountsTable.getSelectedRow(), accountsTable.getSelectedColumn())).toString());
            mainForm.populateAccountTable();
        }
    }
}
