package actionListeners;

import domain.Account;
import domain.MoviesList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class watchedMoviesAccountBtnListener implements ActionListener {
    private JTable accountsTable;

    public watchedMoviesAccountBtnListener(JTable accountsTable){
        this.accountsTable = accountsTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (accountsTable.getSelectedRow()>-1){
            new MoviesList().setVisible(true); //accountsTable.getValueAt(accountsTable.getSelectedRow(),0)
        }
    }
}
