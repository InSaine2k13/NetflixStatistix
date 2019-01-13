package actionListeners;

import domain.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshProfilesBtnListener implements ActionListener {
    private MainForm mainForm;

    public RefreshProfilesBtnListener(MainForm mainForm) {
        this.mainForm = mainForm;
    }
//reload the account table
    @Override
    public void actionPerformed(ActionEvent e) {
        mainForm.populateProfileTable();
    }
}