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
            //adds programs to the watchList class so it can be used to place new watchedprograms in the database later
            new WatchList(type, profileTable.getValueAt(profileTable.getSelectedRow(),0).toString(), profileTable.getValueAt(profileTable.getSelectedRow(),1).toString()).setVisible(true);
        }
    }
}
