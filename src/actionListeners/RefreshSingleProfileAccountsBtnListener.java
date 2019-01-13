package actionListeners;

import domain.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshSingleProfileAccountsBtnListener implements ActionListener {
    private MainForm mainForm;

    public RefreshSingleProfileAccountsBtnListener( MainForm mainForm) {
        this.mainForm = mainForm;
    }
//reload the account table
    @Override
    public void actionPerformed(ActionEvent e) {
        mainForm.populateSingleProfileAccountsTable();
    }
}