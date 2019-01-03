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
    private String AccountID;
    private JTextField percentage;

    public SaveWatchList(String type, String account, JTable watchTable, JTable episode, String AccountID, JTextField Percentage) {
        this.type = type;
        this.watchTable = watchTable;
        Episode = episode;
        this.account = account;
        this.AccountID=AccountID;
        this.percentage=Percentage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String percentageInText = percentage.getText();
        if (type.equals("Serie")){
            DAOProfile.getInstance().newWatchedSerie(watchTable.getValueAt(watchTable.getSelectedRow(),0).toString(),AccountID , Episode.getValueAt(Episode.getSelectedRow(),0).toString(), account,watchTable.getValueAt(watchTable.getSelectedRow(),1).toString(), percentageInText);
        } else if(type.equals("Film")){
          DAOProfile.getInstance().newWatchedFilm(watchTable.getValueAt(watchTable.getSelectedRow(),0).toString(),AccountID, account,watchTable.getValueAt(watchTable.getSelectedRow(),1).toString(), percentageInText);
        }
}
}
