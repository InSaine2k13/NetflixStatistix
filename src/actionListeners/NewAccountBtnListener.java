package actionListeners;

import domain.Account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAccountBtnListener implements ActionListener {

    //code which makes a new Account so the form to make a Account will pop-up
    @Override
    public void actionPerformed(ActionEvent e) {
        new Account().setVisible(true);
    }
}
