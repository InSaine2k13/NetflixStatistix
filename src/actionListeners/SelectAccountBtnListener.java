package actionListeners;

import domain.AccountsList;
import applicationlayer.FilmController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import domain.Film;
import java.util.Set;

public class SelectAccountBtnListener implements ActionListener {

    private JList w;
    private JList account;

    public SelectAccountBtnListener(JList watchedMoviesList, JList a) {
        w = watchedMoviesList;
        account = a;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
        String a = account.getSelectedValue().toString();
        w.setModel(new DefaultListModel());
        Set<Film> Films = FilmController.getInstance().readWatchedFilmsForAccount(a);
        DefaultListModel model = (DefaultListModel) w.getModel();
        for (Film f : Films) {
            model.addElement(f.getTitle());
        }
=======

        new AccountsList().setVisible(true);
>>>>>>> 38ec72daaf8acad56dce0f3dcc93a20fe848884c
    }
}