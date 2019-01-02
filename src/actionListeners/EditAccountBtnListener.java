package actionListeners;

import domain.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAccountBtnListener implements ActionListener {
    private JTable accountsTable;

    public EditAccountBtnListener(JTable accountsTable){
        this.accountsTable = accountsTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (accountsTable.getSelectedRow()>-1){
            new Account(accountsTable.getValueAt(accountsTable.getSelectedRow(),0).toString(), accountsTable.getValueAt(accountsTable.getSelectedRow(),1).toString(), accountsTable.getValueAt(accountsTable.getSelectedRow(),2).toString(), accountsTable.getValueAt(accountsTable.getSelectedRow(),3).toString(), accountsTable.getValueAt(accountsTable.getSelectedRow(),4).toString(), null).setVisible(true);
        }
    }
}
