package actionListeners;

import domain.Profile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SelectMovieBtnListener implements ActionListener {
private JTable accountTable;

    public SelectMovieBtnListener(JTable accountsTable){
        this.accountTable = accountsTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (accountTable.getSelectedRow() > -1) {
            new Profile(accountTable).setVisible(true);
        }
    }
}
