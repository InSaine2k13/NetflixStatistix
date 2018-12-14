package actionListeners;

import domain.MainForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshAccountsBtnListener implements ActionListener {
    private JTable accountsTable;
    private MainForm mainForm;

    public RefreshAccountsBtnListener(JTable accountsTable, MainForm mainForm) {
        this.accountsTable = accountsTable;
        this.mainForm = mainForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainForm.populateAccountTable();
    }
}