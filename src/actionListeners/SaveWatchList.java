package actionListeners;

import datalayer.DAOProfile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveWatchList implements ActionListener {
    private String type;
    private JTable watchTable;
    private JTable Episode;
    private String account;

    public SaveWatchList(String type, String account, JTable watchTable, JTable episode) {
        this.type = type;
        this.watchTable = watchTable;
        Episode = episode;
        this.account = account;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (type.equals("Serie")){
            DAOProfile.getInstance().newWatchedSerie(watchTable.getValueAt(watchTable.getSelectedRow(),0).toString(), Episode.getValueAt(Episode.getSelectedRow(),0).toString(), account);
        } else if(type.equals("Film")){
            DAOProfile.getInstance().newWatchedSerie(watchTable.getValueAt(watchTable.getSelectedRow(),0).toString(), Episode.getValueAt(Episode.getSelectedRow(),0).toString(), account);
        }
    }
}
