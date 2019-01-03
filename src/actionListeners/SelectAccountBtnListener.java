package actionListeners;

import domain.AccountsList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectAccountBtnListener implements ActionListener {

    public SelectAccountBtnListener(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new AccountsList().setVisible(true);
    }
}