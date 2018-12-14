package actionListeners;

import domain.Account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAccountBtnListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new Account().setVisible(true);
    }
}
