package actionListeners;

import domain.AccountsList;
import applicationlayer.FilmController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import domain.Film;
import java.util.Set;

public class SelectAccountBtnListener implements ActionListener {

    private JTable w;
    private JList account;

    public SelectAccountBtnListener(JTable watchedMoviesList, JList a) {
        w = watchedMoviesList;
        account = a;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //get all watched films from the selected account
        String a = account.getSelectedValue().toString();
        w.setModel(new DefaultTableModel( new Object [][] {

        },
                new String [] {
                        "Titel"
                }
        ));
        Set<Film> Films = FilmController.getInstance().readWatchedFilmsForAccount(a);
        DefaultTableModel model = (DefaultTableModel) w.getModel();
        Object rowData[] = new Object[1];
        for (Film f : Films) {
            rowData[0] = f.getTitle();
            model.addRow(rowData);
        }
    }
}