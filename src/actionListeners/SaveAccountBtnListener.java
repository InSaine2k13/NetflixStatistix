package actionListeners;

import datalayer.DAOAccount;
import domain.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveAccountBtnListener implements ActionListener {

    private boolean editing = false;
    private JTextField name;
    private JTextField street;
    private JTextField houseNumber;
    private JTextField houseNumberAddition;
    private JTextField residence;


    public SaveAccountBtnListener(boolean editing, JTextField name, JTextField street, JTextField houseNumber, JTextField houseNumberAddition, JTextField residence){
        this.editing = editing;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.houseNumberAddition = houseNumberAddition;
        this.residence = residence;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(editing) {
            DAOAccount.getInstance().saveAccount(name.getText(), street.getText(), houseNumber.getText(), houseNumberAddition.getText(), residence.getText());
        } else {
            DAOAccount.getInstance().newAccount(name.getText(), street.getText(), houseNumber.getText(), houseNumberAddition.getText(), residence.getText());
        }
    }
}
