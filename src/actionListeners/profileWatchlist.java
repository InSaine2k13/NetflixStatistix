package actionListeners;

import domain.WatchList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class profileWatchlist implements ActionListener {
    private JTable profileTable;
    private String type;

    public profileWatchlist(JTable profileTable, String type) {
        this.profileTable=profileTable;
        this.type=type;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (profileTable.getSelectedRow()>-1){
            new WatchList(type, profileTable.getValueAt(profileTable.getSelectedRow(),0).toString(), profileTable.getValueAt(profileTable.getSelectedRow(),1).toString()).setVisible(true);
        }
    }
}
