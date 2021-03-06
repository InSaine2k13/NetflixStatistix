package actionListeners;

import domain.MainForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshAccountsBtnListener implements ActionListener {
    private MainForm mainForm;

    public RefreshAccountsBtnListener( MainForm mainForm) {
        this.mainForm = mainForm;
    }
//reload the account table
    @Override
    public void actionPerformed(ActionEvent e) {
        mainForm.populateAccountTable();
    }
}