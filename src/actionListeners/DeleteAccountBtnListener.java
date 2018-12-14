package actionListeners;

import datalayer.DAOAccount;
import domain.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAccountBtnListener implements ActionListener {
    private JTable accountsTable;

    public DeleteAccountBtnListener(JTable accountsTable){
        this.accountsTable = accountsTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAOAccount.getInstance().delete((accountsTable.getValueAt(accountsTable.getSelectedRow(), accountsTable.getSelectedColumn())).toString());
    }
}
