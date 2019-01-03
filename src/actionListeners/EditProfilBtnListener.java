package actionListeners;

import domain.Profile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.Year;

public class EditProfilBtnListener implements ActionListener {
    private JTextField id;
    private JTable profileTable;

    public EditProfilBtnListener(JTable profileTable,  JTextField accountnr) {
        this.profileTable = profileTable;
        this.id = accountnr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (profileTable.getSelectedRow() > -1) {
            new Profile(profileTable.getValueAt(profileTable.getSelectedRow(), 0).toString(), null, null, Integer.parseInt(profileTable.getValueAt(profileTable.getSelectedRow(), 1).toString())).setVisible(true);
        }
    }
}
