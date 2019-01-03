package actionListeners;

import domain.MainForm;
import domain.Profile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SelectMovieBtnListener implements ActionListener {
    private MainForm mainForm;
    private JTable selectMovieTable;

    public SelectMovieBtnListener(MainForm mainForm, JTable selectMovieTable){
        this.mainForm = mainForm;
        this.selectMovieTable = selectMovieTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (selectMovieTable.getSelectedRow() > -1) {
            mainForm.populateAmountOfWatchersLabel(Integer.parseInt(selectMovieTable.getValueAt(selectMovieTable.getSelectedRow(), 0).toString()));
        }
    }
}
