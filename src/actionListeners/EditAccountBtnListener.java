package actionListeners;

import domain.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAccountBtnListener implements ActionListener {
    private JTable accountsTable;
// makes it possible for the user to edit the account table in the database
    public EditAccountBtnListener(JTable accountsTable){
        this.accountsTable = accountsTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (accountsTable.getSelectedRow()>-1){
            new Account(Integer.parseInt(accountsTable.getValueAt(accountsTable.getSelectedRow(),0).toString()), accountsTable.getValueAt(accountsTable.getSelectedRow(),1).toString(), accountsTable.getValueAt(accountsTable.getSelectedRow(),2).toString(), accountsTable.getValueAt(accountsTable.getSelectedRow(),3).toString(), accountsTable.getValueAt(accountsTable.getSelectedRow(),4).toString(), accountsTable.getValueAt(accountsTable.getSelectedRow(),5).toString(),  null).setVisible(true);
        }
    }
}
