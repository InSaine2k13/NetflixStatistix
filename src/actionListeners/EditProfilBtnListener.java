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
    private JTextField Edit;

    public EditProfilBtnListener(JTable profileTable, JTextField Edit, JTextField accountnr){
        this.profileTable = profileTable;
        this.Edit=Edit;
        this.id=accountnr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Profile p =new Profile(profileTable.getValueAt(profileTable.getSelectedRow(),0).toString(),null,null);
        p.Edit(p,this.Edit.getText(), id.getText());
    }
}
