package actionListeners;

import domain.Profile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SelectMovieBtnListener implements ActionListener {
private JTable selectMovieTable;

    public SelectMovieBtnListener(JTable selectMovieTable){
        this.selectMovieTable = selectMovieTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (selectMovieTable.getSelectedRow() > -1) {
            //GetAmountWatchedByFilm(0); //selectMovieTable.getValueAt(selectMovieTable.getSelectedRow(), 0)
        }
    }
}
