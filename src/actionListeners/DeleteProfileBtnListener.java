package actionListeners;

import datalayer.DAOProfile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProfileBtnListener implements ActionListener {
    private JTable ProfileTable;

    public DeleteProfileBtnListener(JTable profileTable) {
        this.ProfileTable=profileTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ProfileTable.getSelectedRow()>-1){
        DAOProfile.getInstance().delete((ProfileTable.getValueAt(ProfileTable.getSelectedRow(), ProfileTable.getSelectedColumn())).toString());
    }
    }
}
